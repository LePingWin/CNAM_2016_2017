         =   <        ��������e-�M֚�Xm�G��]��|            u

all: rapport.pdf

%.pdf: %.rst
	rst2pdf -o "$@" -l fr $<

