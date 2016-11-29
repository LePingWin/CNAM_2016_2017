" TODO:
" fix showmode awfulness
" Disassembly mapping
" error handling
" multi-packet messages
"
if !exists("g:ooze_connection")
    let g:ooze_connection = 0
endif

let s:pending = ''

function! s:IsString(a) " {{{
    return type(a:a) == 1
endfunction " }}}

function! s:GetString(msg, key, extra) " {{{
    let val = get(a:msg, a:key)
    if s:IsString(val)
        return val . a:extra
    else
        return ''
    endif
endfunction " }}}

let g:ooze_scratch_buffer_name = '__OozeScratch__'
let g:ooze_traceback_buffer_name = '__OozeTraceback__'

function! s:OpenOozeScratch(contents) " {{{
    if bufname('%') != g:ooze_scratch_buffer_name
        " TODO: go to the window if it's already showing...
        wincmd s
        execute "edit " . g:ooze_scratch_buffer_name
    endif

    set filetype=lisp
    setlocal foldlevel=99
    setlocal buftype=nofile
    setlocal bufhidden=hide
    setlocal noswapfile
    setlocal buflisted

    setlocal noreadonly
    normal! gg"_dG
    call append(0, a:contents)
    setlocal readonly
endfunction " }}}
function! s:DumpTraceback(frames) " {{{
    let current = bufnr('%')
    let bn = bufnr(g:ooze_traceback_buffer_name)

    if bn == -1
        execute "edit " . g:ooze_traceback_buffer_name
        setlocal buftype=nofile
        setlocal bufhidden=hide
        setlocal noswapfile
        setlocal buflisted
    else
        execute "buffer " . bn
    endif

    normal! gg"_dG

    for frame in a:frames
        let call_form = frame[0]
        let file = frame[1]
        let line = frame[2]

        call append(line('$'), call_form . "\t" . file . "\t" . line)
    endfor
    normal! gg"_dd
    set errorformat=%m\	%f\	%l,%m\	%f\	
    execute "cbuffer"

    execute "buffer " . current

    return 1
endfunction " }}}

function! s:HandleMacroexpand(msg) " {{{
    let moutput = s:GetString(a:msg, 'macroexpand-1', "")
    call s:OpenOozeScratch(split(moutput, "\n"))
endfunction " }}}
function! s:HandleStackTrace(msg) " {{{
    call s:DumpTraceback(get(a:msg, 'stack-trace'))

    let output = ''

    let output .= s:GetString(a:msg, 'error', "\n\n")
    let output .= s:GetString(a:msg, 'original', "\n\n")

    if output != ''
        echo substitute(output, '\n\+$', '', '')
    endif

    copen
endfunction " }}}

function! s:HandleMessage(msg) " {{{
    if has_key(a:msg, 'macroexpand-1')
        return s:HandleMacroexpand(a:msg)
    endif
    if has_key(a:msg, 'stack-trace')
        return s:HandleStackTrace(a:msg)
    endif

    let output = ''
    let output .= s:GetString(a:msg, 'stdout', "")
    let output .= s:GetString(a:msg, 'stderr', "")
    let output .= s:GetString(a:msg, 'value', "")

    let output .= s:GetString(a:msg, 'function-arglist', "\n\n")
    let output .= s:GetString(a:msg, 'function-docstring', "\n")
    if output != ''
        echo substitute(output, '\n\+$', '', '')
    endif
endfunction " }}}

function! s:HandleData(data) " {{{
    let s:pending .= a:data

    try
        let messages = bencode#BdecodeAll(s:pending)
    catch /bencode.*truncated/
        return
    endtry

    let s:pending = ''

    for msg in messages
        call s:HandleMessage(msg)
    endfor
endfunction " }}}

" NeoVim job-handling {{{

silent! function s:JobHandler(job_id, data, event) " {{{
    if a:event == 'stdout'
        call s:HandleData(join(a:data, "\n"))
    elseif a:event == 'stderr'
        1
    else
        1
    endif
endfunction " }}}

let s:callbacks = {
\ 'on_stdout': function('s:JobHandler'),
\ 'on_stderr': function('s:JobHandler'),
\ 'on_exit': function('s:JobHandler')
\ }

function! OozeSendMessage(msg)
    if !g:ooze_connection
        throw "Not connected!"
    endif
    try
        call jobsend(g:ooze_connection, bencode#Bencode(a:msg))
    catch /E900/
        echo "Ooze connection died!"
        let g:ooze_connection = 0
    endtry
endfunction!

" }}}

function! s:FindPackage() " {{{
    if !exists("b:ooze_buffer_package")
        let view = winsaveview()

        let result = ""
        call cursor(1, 1)
        if search('\v^\(in-package>\s*(\n\s*)?.', 'c', 40)
            normal! W
            execute "normal v\<plug>(sexp_inner_element)"
            let old_z = @z
            normal! "zy
            let result = @z
            let b:ooze_buffer_package = result
            let @z = old_z
        endif

        call winrestview(view)
        return result
    else
        return b:ooze_buffer_package
    endif

    return ""
endfunction " }}}

function! OozeDisconnect() " {{{
    if g:ooze_connection
        call jobstop(g:ooze_connection)
        let g:ooze_connection = 0
    endif
endfunction " }}}
function! OozeConnectToPort(port) " {{{
    if g:ooze_connection
        call OozeDisconnect()
    endif
    let g:ooze_connection = jobstart(['nc', 'localhost', a:port], s:callbacks)
endfunction " }}}
function! OozeConnect() " {{{
    call OozeConnectToPort('8675')
endfunction " }}}

function! OozeMacroexpand(form) " {{{
    let msg = {"op": "macroexpand", "form": a:form}

    let package = s:FindPackage()
    if package != ""
        let msg["in-package"] = package
    endif

    call OozeSendMessage(msg)
endfunction " }}}
function! OozeMacroexpandSelection() " {{{
    let z = @z
    normal! gv"zy
    call OozeMacroexpand(@z)
    let @z = z
endfunction " }}}

function! OozeDocument(symbol) " {{{
    let msg = {"op": "documentation", "symbol": a:symbol}

    let package = s:FindPackage()
    if package != ""
        let msg["in-package"] = package
    endif

    call OozeSendMessage(msg)
endfunction " }}}
function! OozeDocumentSelection() " {{{
    let z = @z
    normal! gv"zy
    call OozeDocument(@z)
    let @z = z
endfunction " }}}
function! OozeDocumentFormHead() " {{{
    let view = winsaveview()
    execute "normal v\<plug>(sexp_inner_list)o\<plug>(sexp_inner_element)"
    call OozeDocumentSelection()
    call winrestview(view)
endfunction " }}}

function! OozeArglist(symbol) " {{{
    if !g:ooze_connection
        return
    endif
    let msg = {"op": "arglist", "symbol": a:symbol}

    let package = s:FindPackage()
    if package != ""
        let msg["in-package"] = package
    endif

    set noshowmode
    call OozeSendMessage(msg)
endfunction " }}}
function! OozeArglistSelection() " {{{
    let z = @z
    normal! gv"zy
    call OozeArglist(@z)
    let @z = z
endfunction " }}}
function! OozeArglistFormHead() " {{{
    let view = winsaveview()

    let syntaxElement = synIDattr(synIDtrans(synID(line("."),col("."),1)),"name")

    if syntaxElement == "Comment" || syntaxElement == "String"
        " bail if we're in a comment or string
        " TODO: make this suck less
    else
        execute "normal v\<plug>(sexp_inner_list)o\<plug>(sexp_inner_element)"
        call OozeArglistSelection()
    endif

    call winrestview(view)
endfunction " }}}

function! OozeEval(code) " {{{
    let msg = {"op": "eval", "code": a:code}

    let package = s:FindPackage()
    if package != ""
        let msg["in-package"] = package
    endif

    call OozeSendMessage(msg)
endfunction " }}}
function! OozeEvalSelection() " {{{
    let z = @z
    normal! gv"zy
    call OozeEval(@z)
    let @z = z
endfunction " }}}

function! OozeLoad(path) " {{{
    let msg = {"op": "load-file", "path": a:path}
    call OozeSendMessage(msg)
endfunction " }}}
function! OozeLoadCurrent() " {{{
    call OozeLoad(expand('%:p'))
endfunction " }}}

function! OozeSelectTopLevelForm() " {{{
    execute "normal v\<Plug>(sexp_outer_top_list)"
endfunction " }}}

function! OozeHyperspec(symbol) " {{{
    vnew
    call termopen('clhs ' . a:symbol)
    normal! a
endfunction " }}}
function! OozeHyperspecForm() " {{{
    let view = winsaveview()
    let z = @z
    execute "normal v\<plug>(sexp_inner_element)o\<plug>(sexp_inner_element)"
    normal! gv"zy
    call OozeHyperspec(@z)
    let @z = z
    call winrestview(view)
endfunction " }}}

function! OozeSpaceMap() " {{{
    if exists("b:ooze_vblock") || mode() == "R"
        " Don't fuck with visual block mode or replace mode
        return "\<space>"
    else
        return "\<space>\<esc>:\<c-u>call OozeArglistFormHead()\<cr>a"
    endif
endfunction " }}}
function! OozeTrackVblock() " {{{
    " end my life
    let b:ooze_vblock = 1
    return "I"
endfunction " }}}
function! OozeUntrackVblock() " {{{
    " end my life
    if exists("b:ooze_vblock")
        unlet b:ooze_vblock
    endif
endfunction " }}}

function! OozeMapKeys() " {{{
    " [C]onnect and [K]ill
    nnoremap <buffer> <localleader>C :call OozeConnect()<cr>
    nnoremap <buffer> <localleader>K :call OozeDisconnect()<cr>

    " [h]yperspec
    nnoremap <buffer> <localleader>h :call OozeHyperspecForm()<cr>
    nnoremap <buffer> <localleader>H :call OozeHyperspec(input("? "))<cr>

    " [e]val ([f]orm)
    nnoremap <buffer> <localleader>E :call OozeEval(input("? "))<cr>
    vnoremap <buffer> <localleader>e :<c-u>call OozeEvalSelection()<cr>
    nnoremap <buffer> <localleader>e mz:call OozeSelectTopLevelForm()<cr>:<c-u>call OozeEvalSelection()<cr>`z
    nnoremap <buffer> <localleader>f mzvab:<c-u>call OozeEvalSelection()<cr>`z

    " [M]anual and [D]ocument
    nnoremap <buffer> <localleader>D :call OozeDocument(input("? "))<cr>
    inoremap <buffer> <silent> <c-d> <c-o>:<c-u>call OozeDocumentFormHead()<cr>
    nnoremap <buffer> M mzviw:<c-u>call OozeDocumentSelection()<cr>`z

    " [m]acroexpand
    nnoremap <buffer> <localleader>M :call OozeMacroexpand(input("? "))<cr>
    nnoremap <buffer> <localleader>m mzvab:<c-u>call OozeMacroexpandSelection()<cr>`z

    " [r]eload
    nnoremap <buffer> <localleader>r :call OozeLoadCurrent()<cr>

    " magic arglist shit
    inoremap <buffer> <silent> <expr> <space> OozeSpaceMap()
    vnoremap <buffer> <silent> <expr> I       OozeTrackVblock()
    augroup ooze_vblock
        au!
        autocmd InsertLeave <buffer> call OozeUntrackVblock()
    augroup END
endfunction " }}}

augroup ooze_dev " {{{
    au!
    autocmd BufWritePost ooze.vim source %
augroup END " }}}

augroup ooze_showmode_save
    au!
    " We save showmode around insert mode because the arglist command needs
    " to disable it.
    au InsertEnter * :let g:ooze_save_showmode=&showmode
    au InsertLeave * :let &showmode=g:ooze_save_showmode
    au InsertLeave * :let &showmode=g:ooze_save_showmode
augroup END
