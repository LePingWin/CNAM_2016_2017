" Encoding --------------------------------------------------------------------
function! bencode#Bencode(o) " {{{
    let typ = type(a:o)
    if typ == 0
        return s:BencodeInteger(a:o)
    elseif typ == 1
        return s:BencodeString(a:o)
    elseif typ == 3
        return s:BencodeList(a:o)
    elseif typ == 4
        return s:BencodeDict(a:o)
    else
        throw "sorry m8 i can't bencode that"
    endif
endfunction " }}}

function! s:BencodeInteger(o) " {{{
    return "i" . string(a:o) . "e"
endfunction " }}}

function! s:BencodeString(o) " {{{
    return string(len(a:o)) . ":" . a:o
endfunction " }}}

function! s:BencodeList(o) " {{{
    let contents = map(a:o, 'bencode#Bencode(v:val)')
    return 'l' . join(contents, '') . 'e'
endfunction " }}}

function! s:BencodeDict(o) " {{{
    let contents = 'd'
    for key in sort(copy(keys(a:o)))
        let contents .= bencode#Bencode(key) . bencode#Bencode(a:o[key])
    endfor
    return contents . 'e'
endfunction " }}}


" Decoding --------------------------------------------------------------------
function! bencode#BdecodeAll(bstring) " {{{
    let results = []
    let remaining = a:bstring
    while remaining !~# '\v^\s*$'
        let dec = s:ActuallyBdecode(remaining)
        call add(results, dec[0])
        let remaining = dec[1]
    endwhile
    return results
endfunction " }}}

function! bencode#Bdecode(bstring) " {{{
    return s:ActuallyBdecode(a:bstring)[0]
endfunction " }}}

function! s:ActuallyBdecode(bstring) " {{{
    let bs = substitute(a:bstring, '\v^\s*', '', '')

    if bs == ''
        throw "bencode: received truncated data"
    endif

    if bs[0] == 'i'
        return s:BdecodeInteger(bs)
    elseif bs[0] =~ '[0-9]'
        return s:BdecodeString(bs)
    elseif bs[0] == 'l'
        return s:BdecodeList(bs)
    elseif bs[0] == 'd'
        return s:BdecodeDict(bs)
    else
        throw "shits fucked, yo"
    endif
endfunction " }}}

function! s:FindDelimiter(bstring, delimiter) "{{{
    let i = 1
    let len = len(a:bstring)

    while a:bstring[i] != a:delimiter
        if i >= len
            throw "bencode: received truncated data"
        endif

        let i += 1
    endwhile

    return i
endfunction " }}}

function! s:BdecodeInteger(bstring) " {{{
    let i = s:FindDelimiter(a:bstring, 'e')
    return [0 + a:bstring[1:i - 1], a:bstring[i+1:]]
endfunction " }}}

function! s:BdecodeString(bstring) " {{{
    let i = s:FindDelimiter(a:bstring, ':')

    let slen = 0 + a:bstring[0:i-1]
    let strcontents = a:bstring[i+1:i+slen]

    if len(strcontents) < slen
        throw "bencode: received truncated data"
    endif

    return [strcontents, a:bstring[i+slen+1:]]
endfunction " }}}

function! s:BdecodeList(bstring) " {{{
    let data = a:bstring[1:]
    let result = []
    while 1
        let dec = s:ActuallyBdecode(data)
        let val = dec[0]
        let rest = dec[1]
        call add(result, val)
        if rest[0] == 'e'
            "                   strip off the trailing e from the list
            return [result, rest[1:]]
        else
            let data = rest
        endif
    endwhile
endfunction " }}}

function! s:BdecodeDict(bstring) " {{{
    let data = a:bstring[1:]
    let result = {}
    while 1
        let dec = s:ActuallyBdecode(data)
        let key = dec[0]
        let rest = dec[1]

        let dec = s:ActuallyBdecode(rest)
        let result[key] = dec[0]
        let rest = dec[1]

        if rest[0] == 'e'
            "                   strip off the trailing e from the dict
            return [result, rest[1:]]
        else
            let data = rest
        endif
    endwhile
endfunction " }}}
