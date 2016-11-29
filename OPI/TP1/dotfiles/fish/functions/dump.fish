function dump -d "dump to/from the internet"
    hg -R ~/.plan push
    hg -R ~/.plan push git

    hg -R ~/src/cl-nrepl push

    hg -R ~/src/ru push

    hg -R ~/src/temperance push
    hg -R ~/src/temperance push git

    hg -R ~/src/scully push
    hg -R ~/src/scully push git

    hg -R ~/src/mazes push
    hg -R ~/src/mazes push git

    hg -R ~/src/cl-ggp push
    hg -R ~/src/cl-ggp push git

    hg -R ~/src/sand push
    hg -R ~/src/sand push git

    hg -R ~/src/hype push
    hg -R ~/src/hype push git

    hg -R ~/src/cl-losh push
    hg -R ~/src/cl-losh push git

    hg -R ~/src/beast push
    hg -R ~/src/beast push git

    hg -R ~/src/vex push
    hg -R ~/src/vex push git

    hg -R ~/lib/dotfiles push

    offlineimap -qf INBOX
end
