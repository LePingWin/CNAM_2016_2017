" if exists('loaded_lispfolding') || &cp
"     finish
" endif
let loaded_lispfolding=1

let s:lispfold_flet_re = '\vlabels|flet'
if !exists('g:lisp_fold_extra')
    let g:lisp_fold_extra = []
endif

function! s:LispFoldingFormIsFlet() " {{{
    " Return whether the form the cursor is on is a fletlike.
    let old_z = @z

    " Yank the next word.
    normal! l
    silent normal! "zyiw
    let word = @z

    let @z = old_z

    " See if that next word is a fletlike thing.
    if word =~ s:lispfold_flet_re
        return 1
    else
        return 0
    endif
endfunction " }}}
function! s:LispFoldingStartFlet(lnum) " {{{
    " Return 1 when the given line is the start of a multi-line flet'ed
    " function definition.  We want to fold those.  Return -1 if it's
    " a single-line fletted definition.  Return 0 if it's neither.
    "
    " Relies on things being indented correctly to help the speed.
    "
    " Basically this thing is a total shitshow, turn back now.
    "
    " TODO: the function definitions have to be indented, fix that
    let l = getline(a:lnum)
    let save_cursor = getpos('.')

    try
        " A foldable flet looks like this:
        "
        " (flet
        "     ((foo ()
        "        ...)
        "      (bar ()
        "        ...))
        "   body)
        "

        " Make sure the cursor's on the current line.
        call setpos('.', [0, a:lnum, 1, 1])

        " Check if the line starts with ( or ((, and move to the appropriate
        " "start of the function form" character.
        if l =~ '\v^\s\s\s\s+\(\(\k+( \(|$)'
            normal! ^l
        elseif l =~ '\v^\s\s\s\s\s+\(\k+( \(|$)'
            normal! ^
        else
            return 0
        endif

        let save_start = getpos('.')

        " Pop up two levels in the paren stack.
        " TODO: make sure we actually do, not that it matters in practice
        call searchpair('(', '', ')', 'b')
        call searchpair('(', '', ')', 'b')

        if !s:LispFoldingFormIsFlet()
            return 0
        endif

        " We know this is a fletthing, but if it only spans one line, bail.
        call setpos('.', save_start)
        if searchpairpos('(', '', ')')[0] == a:lnum
            return -1
        end

        " congrats, u made it
        return 1
    finally
        call setpos('.', save_cursor)
    endtry
endfunction " }}}
function! s:LispFoldingEndFlet(lnum) " {{{
    " Return whether we're at the last line of a multi-line fletted function.
    let l = getline(a:lnum)
    let save_cursor = getpos('.')

    try
        if l =~ '\v\)\)$'
            call setpos('.', [0, a:lnum, len(l) - 1, 1])
            let start_line = searchpairpos('(', '', ')', 'b')[0]

            let r = s:LispFoldingStartFlet(start_line)
            if r == 1
                return 1
            elseif r == -1
                return 0
            endif
        endif

        if l =~ '\v\)$'
            call setpos('.', [0, a:lnum, len(l), 1])
            let start_line = searchpairpos('(', '', ')', 'b')[0]

            if s:LispFoldingStartFlet(start_line) == 1
                return 1
            endif
        endif

        return 0
    finally
        call setpos('.', save_cursor)
    endtry
endfunction " }}}

function! s:ShouldFoldToplevel(line) " {{{
    for pat in g:lisp_fold_extra
        if a:line =~ ('\v^\(' . pat)
            return 1
        endif
    endfor

    return 0
endfunction " }}}

function! GetLispFold(lnum) " {{{
    let save_cursor = getpos('.')
    let save_search = @/
    let inline_fn_comment_re = '\v^\s*;;( .*)?$'

    try
        if getline(a:lnum) =~ '^;;;; '
            " Never fold top-level header comments
            return "0"
        elseif getline(a:lnum) =~ '^;;; '
            " Subheader top level comments should get folded together in level 1
            return "1"
        elseif getline(a:lnum) =~ inline_fn_comment_re
            " Inline function comments should increment the fold level
            let prev = getline(a:lnum - 1) =~ inline_fn_comment_re
            let next = getline(a:lnum + 1) =~ inline_fn_comment_re

            if (!prev) && next
                return "a1"
            elseif prev && (!next)
                return "s1"
            else
                return "="
            endif
        elseif getline(a:lnum) =~ '^; '
            " don't include commentary-commented lines in deeper folds than necessary
            return "-1"
        elseif s:ShouldFoldToplevel(getline(a:lnum))
            return ">1"
        elseif getline(a:lnum) =~ '^(def'
            " fuck it just fold everything that looks kinda deffy
            return ">1"
        elseif getline(a:lnum) =~ '^(let '
            " let over lambda
            return ">1"
        elseif getline(a:lnum) =~ '^(in-package'
            return ">1"
        elseif getline(a:lnum) =~ '^(adt:defdata'
            return ">1"
        elseif getline(a:lnum) =~ '^(opts:define-opts'
            return ">1"
        elseif getline(a:lnum) =~ '^$' && getline(a:lnum - 1) =~ '^$'
            return "0"
        elseif getline(a:lnum) =~ '^$'
            " Single blank lines fold along with the previous line, so that the
            " blank line after a defun gets folded into the defun.
            return "="
        " elseif LispFoldingStartFlet(a:lnum) == 1
        "     " if this is a function definition in a labels/flet/etc, we want to
        "     " start a new deeper fold
        "     return ">2"
        " elseif s:LispFoldingEndFlet(a:lnum)
        "     " if this is the END of a flet definition, we should pop a foldlevel
        "     return "<2"
        else
            return "="
        endif
    finally
        call setpos('.', save_cursor)
        let @/ = save_search
    endtry
endfunction " }}}

function! TurnOnLispFolding() " {{{
    setlocal foldexpr=GetLispFold(v:lnum)
    setlocal foldmethod=expr
    " nnoremap <buffer> qq :echo GetLispFold(getpos('.')[1])<cr>
endfunction " }}}
