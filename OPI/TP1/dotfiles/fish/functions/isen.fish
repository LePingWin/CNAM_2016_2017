function isen -d "Translate from Icelandic to English" --wraps "trans"
    trans -b -u firefox is:en $argv
end
