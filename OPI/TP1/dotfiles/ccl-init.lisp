(setf *quit-on-eof* t)
(setq *load-preserves-optimization-settings* t)

(defun sjl-lisp-prompt-format (stream level)
  (if (zerop level)
    (format stream "~%[ClozureCL] ~A> " (package-name *package*))
    (format stream "~%[~d] > " level)))

(setf ccl:*listener-prompt-format* #'sjl-lisp-prompt-format)


;;; The following lines added by ql:add-to-init-file:
#-quicklisp
(let ((quicklisp-init (merge-pathnames ".quicklisp/setup.lisp" (user-homedir-pathname))))
  (when (probe-file quicklisp-init)
    (load quicklisp-init)))

