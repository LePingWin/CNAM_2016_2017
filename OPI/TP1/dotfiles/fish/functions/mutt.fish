set -g -x MUTT_BIN (which mutt)

function mutt
    bash --login -c "cd ~/Desktop; $MUTT_BIN \$@" custom_mutt $argv
end
