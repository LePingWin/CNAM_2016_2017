" ============================================================================
" File:        neorepl.vim
" Description: Send text from neovim buffers to neovim terminals.
" Maintainer:  Steve Losh <steve@stevelosh.com>
" License:     MIT/X11
" ============================================================================

" Init {{{

if !exists('g:neorepl_debug') && (exists('loaded_neorepl') || &cp)
    finish
endif

let loaded_neorepl = 1
let g:neorepl_jobid = 0

"}}}
" Functions {{{

function! s:NeoRepl(command) " {{{
    vnew
    let result = termopen(a:command)

    let g:neorepl_jobid = result
endfunction " }}}

function! NeoReplSendRaw(payload) " {{{
    for line in (split(a:payload, '\n'))
        call jobsend(g:neorepl_jobid, line . "\n")
        sleep 2m " please kill me
    endfor
endfunction " }}}

function! NeoReplSendSelection() " {{{
    let old_z = @z
    normal! gv"zy

    call NeoReplSendRaw(@z . "\n")
    let @z = old_z
endfunction " }}}

" }}}
" Command {{{

command! -range=0 -complete=shellcmd -nargs=1 NeoRepl call s:NeoRepl(<q-args>)

" }}}
