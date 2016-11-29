if exists("b:current_syntax")
    finish
endif

syntax region ccsComment start=+*+ end=+$+
syntax keyword ccsKeyword prop
syntax keyword ccsConstant tt ff
syntax match ccsAll '\v\[\k+\]'
syntax match ccsExists '\v\<\k+\>'

highlight default link ccsComment Comment
highlight default link ccsConstant Constant
highlight default link ccsKeyword Keyword
highlight default link ccsAll Macro
highlight default link ccsExists Function

let b:current_syntax = "mu"
