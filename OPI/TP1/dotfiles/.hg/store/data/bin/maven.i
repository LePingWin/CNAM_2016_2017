         A   @      ���������H��Ʉ#�v�X�H�*ǹ�H            u#!/usr/bin/env bash

set -e

mvn "$@" | grcat ~/.grc/conf.maven
     A     i   z      �    ����$X�"�7���Y��Z���
�                  @   ]mvn "$@" | grep --line-buffered -Ev 'Including.*in the shaded jar' | grcat ~/.grc/conf.maven
