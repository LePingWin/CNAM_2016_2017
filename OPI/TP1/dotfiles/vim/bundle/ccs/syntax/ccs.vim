if exists("b:current_syntax")
    finish
endif

syntax region ccsComment start=+*+ end=+$+
syntax keyword ccsKeyword proc agent prop set checkprop echo
syntax keyword ccsKeyword min max tau eps
syntax keyword ccsConstant nil
syntax match ccsOperator '\v\.'
syntax match ccsOperator '\v\+'
syntax match ccsOutput '\v\'\k+'

" non very magic because I dunno how to make vim use the fuckin \< pattern
" with very magic, whatever idc
syntax match ccsInput '\<[a-z]\k\+'

syntax region ccsString start=/"/ skip=/\\\\\|\\"/ end=/"/ contains=@Spell

highlight default link ccsComment Comment
highlight default link ccsString String
highlight default link ccsConstant Constant
highlight default link ccsKeyword Keyword
highlight default link ccsOperator Operator
highlight default link ccsOutput Macro
highlight default link ccsInput Function

let b:current_syntax = "ccs"
