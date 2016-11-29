var css = '* { -webkit-transition: none !important; -moz-transition: none !important; -o-transition: none !important; -ms-transition: none !important; transition: none !important; }'
var head = document.head || document.getElementsByTagName('head')[0]
var style = document.createElement('style');
 
style.type = 'text/css';
if (style.styleSheet){
style.styleSheet.cssText = css;
} else {
style.appendChild(document.createTextNode(css));
}
 
head.appendChild(style);
