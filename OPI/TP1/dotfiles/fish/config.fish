# Useful functions {{{

function eb; nvim ~/Dropbox/bitly.txt; end
function ed; nvim ~/.vim/custom-dictionary.utf-8.add; end
function ef; nvim ~/.config/fish/config.fish; end
function eff; nvim ~/.config/fish/functions; end
function eg; nvim ~/.gitconfig; end
function eh; nvim ~/.hgrc; end
function ei; nvim ~/Dropbox/inventory.markdown; end
function ek; nvim ~/lib/dotfiles/keymando/keymandorc.rb; end
function em; nvim ~/.mutt/muttrc; end
function es; nvim ~/.slate; end
function et; nvim ~/.tmux.conf; end
function ev; nvim ~/.vimrc; end
function ez; nvim ~/lib/dotfiles/zsh; end

function ..;    cd ..; end
function ...;   cd ../..; end
function ....;  cd ../../..; end
function .....; cd ../../../..; end

# I give up
alias :q exit
alias :qa exit

# }}}
# Completions {{{

function make_completion --argument alias command
    complete -c $alias -xa "(
        set -l cmd (commandline -pc | sed -e 's/^ *\S\+ *//' );
        complete -C\"$command \$cmd\";
    )"
end

make_completion g "git"

# }}}
# Bind Keys {{{

# Backwards compatibility?  Screw that, it's more important that our function
# names have underscores so they look pretty.
function jesus_fucking_christ_bind_the_fucking_keys_fish
    bind \cn accept-autosuggestion
    bind \cw backward-kill-word
end
function fish_user_keybindings
    jesus_fucking_christ_bind_the_fucking_keys_fish
end
function fish_user_key_bindings
    jesus_fucking_christ_bind_the_fucking_keys_fish
end

# }}}
# Environment variables {{{

set -g -x NIX_LINK "$HOME/.nix-profile"

function prepend_to_path -d "Prepend the given dir to PATH if it exists and is not already in it"
    if test -d $argv[1]
        if not contains $argv[1] $PATH
            set -gx PATH "$argv[1]" $PATH
        end
    end
end
set -gx PATH "/sbin"
prepend_to_path "/usr/sbin"
prepend_to_path "/bin"
prepend_to_path "/usr/bin"
prepend_to_path "/usr/local/bin"
prepend_to_path "/Library/TeX/texbin/"
# prepend_to_path "/usr/local/share/python"
prepend_to_path "/usr/local/sbin"
prepend_to_path "/usr/local/share/npm/bin"
prepend_to_path "$HOME/lib/dotfiles/bin"
prepend_to_path "$HOME/lib/hg/hg"
prepend_to_path "$HOME/.roswell/bin"
prepend_to_path "$HOME/bin"

set BROWSER open

set -g -x fish_greeting ''
set -g -x EDITOR nvim
set -g -x COMMAND_MODE unix2003
set -g -x RUBYOPT rubygems
set -g -x CLASSPATH "$CLASSPATH:/usr/local/Cellar/clojure-contrib/1.2.0/clojure-contrib.jar"

set -g -x NODE_PATH "/usr/local/lib/node_modules"

set -g -x VIM_BINARY "/usr/local/bin/vim"
set -g -x MVIM_BINARY "/usr/local/bin/mvim"

set -g -x DRIP_SHUTDOWN 30

set -g -x MAVEN_OPTS "-Xmx512M -XX:MaxPermSize=512M"
set -g -x _JAVA_OPTIONS "-Djava.awt.headless=true"

set -g -x GPG_TTY (tty)

function headed_java -d "Put Java into headed mode"
    echo "Changing _JAVA_OPTIONS"
    echo "from: $_JAVA_OPTIONS"
    set -g -e _JAVA_OPTIONS
    echo "  to: $_JAVA_OPTIONS"
end
function headless_java -d "Put Java into headless mode"
    echo "Changing _JAVA_OPTIONS"
    echo "from: $_JAVA_OPTIONS"
    set -g -x _JAVA_OPTIONS "-Djava.awt.headless=true"
    echo "  to: $_JAVA_OPTIONS"
end



# }}}
# Python variables {{{

set -g -x PIP_DOWNLOAD_CACHE "$HOME/.pip/cache"
set -g -x WORKON_HOME "$HOME/lib/virtualenvs"

prepend_to_path "/usr/local/share/python"
prepend_to_path "/usr/local/Cellar/PyPi/3.6/bin"
prepend_to_path "/usr/local/Cellar/python/2.7.1/bin"
prepend_to_path "/usr/local/Cellar/python/2.7/bin"
prepend_to_path "/usr/local/Cellar/python/2.6.5/bin"

set -g -x PYTHONPATH ""
set PYTHONPATH "$PYTHONPATH:/usr/local/lib/python2.7.1/site-packages"
set PYTHONPATH "$PYTHONPATH:/usr/local/lib/python2.7/site-packages"
set PYTHONPATH "$PYTHONPATH:/usr/local/lib/python2.6/site-packages"
set PYTHONPATH "$HOME/lib/python/see:$PYTHONPATH"
set PYTHONPATH "$HOME/lib/hg/hg:$PYTHONPATH"

set -gx WORKON_HOME "$HOME/lib/virtualenvs"
eval (python -m virtualfish)

set -g -x NLTK_DATA "/Users/sjl/src/ru/malv/nltk_data"

# }}}
# Z {{{

. ~/src/z-fish/z.fish

# }}}
# Prompt {{{

set normal (set_color normal)
set magenta (set_color magenta)
set yellow (set_color yellow)
set green (set_color green)
set gray (set_color -o black)
set hg_promptstring "< on $magenta<branch>$normal>< at $yellow<tags|$normal, $yellow>$normal>$green<status|modified|unknown><update>$normal<
patches: <patches|join( → )|pre_applied($yellow)|post_applied($normal)|pre_unapplied($gray)|post_unapplied($normal)>>" 2>/dev/null

function virtualenv_prompt
    if [ -n "$VIRTUAL_ENV" ]
        printf '(%s) ' (basename "$VIRTUAL_ENV")
    end
end

function hg_prompt
    hg prompt --angle-brackets $hg_promptstring 2>/dev/null
end

function git_prompt
    if git root >/dev/null 2>&1
        set_color normal
        printf ' on '
        set_color magenta
        printf '%s' (git currentbranch ^/dev/null)
        set_color green
        git_prompt_status
        set_color normal
    end
end

function prompt_pwd --description 'Print the current working directory, shortend to fit the prompt'
    echo $PWD | sed -e "s|^$HOME|~|"
end

function fish_prompt
    set last_status $status

    z --add "$PWD"

    echo

    set_color magenta
    printf '%s' (whoami)
    set_color normal
    printf ' at '

    set_color yellow
    printf '%s' (hostname|cut -d . -f 1)
    set_color normal
    printf ' in '

    set_color $fish_color_cwd
    printf '%s' (prompt_pwd)
    set_color normal

    hg_prompt
    git_prompt

    echo

    virtualenv_prompt

    if test $last_status -eq 0
        set_color white -o
        printf '><((°> '
    else
        set_color red -o
        printf '[%d] ><((ˣ> ' $last_status
    end

    set_color normal
end

# }}}

if test -f $HOME/.local.fish
    . $HOME/.local.fish
end

true
