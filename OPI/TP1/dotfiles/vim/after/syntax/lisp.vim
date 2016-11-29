" don't know why this doesn't work in very-magic mode but fuck it, whatever,
" this works i don't care
syn match lispKey "\k\@<!:\k\+"
syn match lispDecl "define-\k\+"

syn keyword lispDecl defmethod defvar defparameter

" defstar
syn keyword lispDecl defun* defmethod* defgeneric* defvar* defparameter* flet* labels* lambda* *let

" FiveAM shit
syn keyword lispDecl test
syn keyword lispFunc def-suite in-suite

syn match lispString !#\\[\(\)]!

