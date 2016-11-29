         _           _                     _
     _ _|_|_____ ___| |_ ___ ___ ___ ___ _| |___
    | | | |     |___| . | -_|   |  _| . | . | -_|
     \_/|_|_|_|_|   |___|___|_|_|___|___|___|___|

If you're reading this you probably want to do one of the following:

* Generate a bencoded string of a Vim datastructure
* Parse a bencoded string into a Vim data structure

If so, you're in luck.

API
---

`vim-bencode` provides three functions.

### `bencode#Bencode(vimobject)`

Take a Vim object and return a bencoded string.  Do whatever you want with it.

Example:

    :echo bencode#Bencode([1, "dogs"])
    li1e4:dogse

### `bencode#Bdecode(somestring)`

Take a bencoded string and return a Vim Number/String/List/Dict representing the
first bencoded item in the stream.

If there's more than one thing in the stream the rest will be ignored.

If you pass this function garbage all bets are off.  God help you.

Example:

    :echo bencode#Bdecode("i1ei2e")
    1

### `bencode#BdecodeAll(somestring)`

Take a bencoded string and return a list of Vim objects of all the things that
were in the bencoded string.

God help you if you pass this something that's not valid bencode data.

    :echo bencode#BdecodeAll("i1ei2e")
    [1, 2]


License
=======

MIT/X11
