.. vim: set spelllang=fr ts=4 sw=4 expandtab:

.. role:: right
   :class: right

.. header::

    .. list-table::
      :class: headertable

      * - Bine Loïc
        - .. class:: right

          17/11/2016

.. footer::

    .. class:: footertable

    +------------------------+
    | .. class:: center      |
    |                        |
    | ###Page###/###Total### |
    +------------------------+


======
TP VCS
======

Les changesets de Julien sont identifiés par Loîc Bine suite à l'oubli de suppression du # devant username

ETAPE 1
---------

.. code-block:: sh

    <LBI>~$ mkdir TP-VCS
    <LBI>~$ cd TP-VCS
    <LBI>~/TP-VCS hg clone --noupdate https:://bitbucket.org/nicph/tp-vcs-template ref
    <LBI>~/TP-VCS ls
    ref

ETAPE 2
---------

.. code-block:: sh

    <Aurelie>~/TP-VCS $ hg clone ref Aurelie
    <Aurelie>~/TP-VCS $ ls
    <Aurelie> ref

ETAPE 3
---------

.. code-block:: sh

    <Aurelie>~/TP-VCS $ vi Aurelie/.hg/hgrc

ETAPE 4
---------

.. code-block:: sh

    <Aurelie>~/TP-VCS $ cd Aurelie
    <Aurelie>~/TP-VCS/Aurelie $ touch AUTHORS.txt
    <Aurelie>~/TP-VCS/Aurelie $ vi AUTHORS.txt

ETAPE 5
---------

.. code-block:: sh

    <Aurelie>~/TP-VCS/Aurelie $ hg add AUTHORS.txt
    <Aurelie>~/TP-VCS/Aurelie $ hg commit -m "Ajout du fichier AUTHORS.txt"
    <Aurelie>~/TP-VCS/Aurelie $ hg push

ETAPE 6
---------

.. code-block:: sh

    <Clement>~/TP-VCS $ hg clone ref Clement
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 1 changesets with 2 changes to 2 files
    <Clement>~/TP-VCS $ ls
    <Clement> Clement Aurelie ref

ETAPE 7
---------

.. code-block:: sh

    <Clement>~/TP-VCS $ vi Clement/.hg/hgrc

ETAPE 8
---------

.. code-block:: sh

    <Clement>~/TP-VCS $ vi Clement/README.rst


ETAPE 9
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg commit -m "Modification de README.rst"
    <Clement>~/TP-VCS/Clement $ hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 1 changesets with 2 changes to 2 files

ETAPE 10
---------

.. code-block:: sh

    <Aurelie>~/TP-VCS/Aurelie $ hg pull
    pulling from /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 1 changesets with 2 changes to 2 files
    <Aurelie>~/TP-VCS/Aurelie $ hg update
    <Aurelie>~/TP-VCS/Aurelie $ hg branch LBI-b1

ETAPE 11
---------   

.. code-block:: sh

    <Aurelie>~/TP-VCS/Aurelie $ hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    aucun changement trouvé

On règle le problème en faisant un commit de la branche

.. code-block:: sh

    <Aurelie>~/TP-VCS/Aurelie $ hg commit -m "Ajout de la branche LBI-b1"
    <Aurelie>~/TP-VCS/Aurelie $ hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    abandon : push creates new remote branches: LBI-b1!
    (use 'hg push --new-branch' to create new remote branches)
    <Aurelie>~/TP-VCS/Aurelie $ hg push --new-branch
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 1 changesets with 1 changes to 1 files

ETAPE 12
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg pull
    <Clement>~/TP-VCS/Clement $ hg update

ETAPE 13
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ vi rapport.rst

ETAPE 14
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ make
    rst2pdf -o "rapport.pdf" -l fr rapport.rst


ETAPE 15
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg status
    M rapport.rst
    ? rapport.pdf

ETAPE 16
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg commit rapport.rst -m "Intégration des notes du rapport.rst"

ETAPE 17
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg status
    ? rapport.pdf

On obtient ce résultat car rapport.pdf ne sont pas présent dans un commit.
Lors de l'étape 15 : rapport.pdf et rapport.mst ne sont pas "commité", on le corrige donc dans l'étape 16 pour rapport.mst

ETAPE 18
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ touch .hgignore
    <Clement>~/TP-VCS/Clement $ vi .hgignore

ETAPE 19
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg add .hgignore
    <Clement>~/TP-VCS/Clement $ hg commit .hgignore -m "Ajout d'un hgignore"

ETAPE 20
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 2 changesets with 2 changes to 2 files (+1 heads)

ETAPE 21
---------

.. code-block:: sh

    <Julien>~/TP-VCS $ hg clone ref Julien
    <Julien>~/TP-VCS $ ls
    Aurelie  Clement  Julien  ref

ETAPE 22
---------

.. code-block:: sh

    <Julien>~/TP-VCS $ vi Clement/.hg/hgrc

ETAPE 23
---------

.. code-block:: sh
    
    <Julien>~/TP-VCS $ cd Julien
    <Julien>~/TP-VCS/Julien $ hg update LBI-b1
    1 files updated, 0 files merged, 1 files removed, 0 files unresolved


ETAPE 24
---------

.. code-block:: sh

    <Julien>~/TP-VCS/Julien $ vi rapport.rst
    <Julien>~/TP-VCS/Julien $ hg commit -m "Modification rapport.rst"
    <Julien>~/TP-VCS/Julien $ hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 1 changesets with 1 changes to 1 files

ETAPE 25
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg pull
    <Clement>~/TP-VCS/Clement $ hg update --clean LBI-b1
    1 files updated, 0 files merged, 1 files removed, 0 files unresolved

ETAPE 26
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg log -f
    changeset:   7:6103ffafa9e1
    branch:      LBI-b1
    tag:         tip
    parent:      4:d68304d8aa19
    user:        Julien <jdoe@example.com>
    date:        Thu Nov 17 11:54:39 2016 +0100
    summary:     Modification rapport.rst

    changeset:   4:d68304d8aa19
    branch:      LBI-b1
    user:        Aurelie <jdoe@example.com>
    date:        Thu Nov 17 11:15:07 2016 +0100
    summary:     Ajout de la branche LBI-b1

    changeset:   3:4614223cac66
    user:        Clement <jdoe@example.com>
    date:        Thu Nov 17 11:00:20 2016 +0100
    summary:     Modification de README.rst

    changeset:   2:b93ae8b9b954
    user:        Aurelie <jdoe@example.com>
    date:        Thu Nov 17 10:30:28 2016 +0100
    summary:     Ajout du fichier AUTHORS.txt

    changeset:   1:de233068e18a
    user:        nicph
    date:        Thu Nov 17 07:41:31 2016 +0100
    summary:     remove section numbering

    changeset:   0:d1ee9d72f15b
    user:        nicph
    date:        Sun Oct 30 10:42:51 2016 +0000
    summary:     add basic rst template & makefile

ETAPE 27
---------

.. code-block:: sh
    
    <Clement>~/TP-VCS/Clement $ hg diff -c -1
    <Clement>~/TP-VCS/Clement $ hg diff -r -default

ETAPE 28
---------

.. code-block:: sh
    
    <Clement>~/TP-VCS/Clement $ vi rapport.rst
    <Clement>~/TP-VCS/Clement $ hg commit -m "Modification rapport"

ETAPE 29
---------

.. code-block:: sh

    <Julien>~/TP-VCS/Julien $ hg log

ETAPE 30
---------

.. code-block:: sh
    
    <Julien>~/TP-VCS/Julien $ vi rapport.rst

ETAPE 31
---------

.. code-block:: sh

    <Julien>~/TP-VCS/Julien $ hg commit -m "Modification rapport"

ETAPE 32
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg push

ETAPE 33
---------

.. code-block:: sh

    <Julien>~/TP-VCS/Julien $ hg push -f
    hg push -f
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 3 changesets with 3 changes to 1 files (+1 heads)
    <Julien>~/TP-VCS/Julien $ hg merge 
    hg commit -m "Chronologie Julien"
    <Julien>~/TP-VCS/Julien $ hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    remote has heads on branch 'LBI-b1' that are not known locally: 2978f73be137
    adding changesets
    adding manifests
    adding file changes
    added 1 changesets with 1 changes to 1 files
    merging rapport.rst
    3 fichiers à éditer
     output file rapport.rst appears unchanged
    was merge successful (yn)? n
    <Julien>~/TP-VCS/Julien $ hg push

Julien résout le conflit en sauvegardant son rapport

ETAPE 34
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg pull
    pulling from /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 5 changesets with 5 changes to 1 files (+1 heads)
    (run 'hg heads' to see heads, 'hg merge' to merge)
    <Clement>~/TP-VCS/Clement $ hg update
    0 files updated, 0 files merged, 0 files removed, 0 files unresolved
    1 other heads for branch "LBI-b1"
    [pingwin@pingwinjaro Clement]$ hg update LBI-b1
    1 files updated, 0 files merged, 0 files removed, 0 files unresolved
    <Clement>~/TP-VCS/Clement $ hg merge
    merging rapport.rst
    3 fichiers à éditer
     output file rapport.rst appears unchanged
    was merge successful (yn)? y
    0 files updated, 1 files merged, 0 files removed, 0 files unresolved
    (branch merge, don't forget to commit)
    <Clement>~/TP-VCS/Clement $ hg commit -m "Synchro Depot"

    <Clement>~/TP-VCS/Clement $ vi LICENSE.txt
    <Clement>~/TP-VCS/Clement $ hg add LICENSE.txt 
    <Clement>~/TP-VCS/Clement $ hg commit -m "Ajout Licence"

ETAPE 35
---------

.. code-block:: sh
    
    <Clement>~/TP-VCS/Clement $ touch ROADMAP.txt
    <Clement>~/TP-VCS/Clement $ hg add ROADMAP.txt
    <Clement>~/TP-VCS/Clement $ hg commit -m "Ajout ROADMAP"

ETAPE 36
---------

.. code-block:: sh
    
    <Clement>~/TP-VCS/Clement $ hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 3 changesets with 3 changes to 3 files (-1 heads)

ETAPE 37
---------

.. code-block:: sh
    
    <Julien>~/TP-VCS/Julien $  hg commit -m "Ajout notes"

ETAPE 38
---------

.. code-block:: sh

    <Julien>~/TP-VCS/Julien $ hg incoming
    comparaison avec /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    changeset:   12:2978f73be137
    branch:      LBI-b1
    parent:      8:5ff28a8aed0b
    user:        Clement <jdoe@example.com>
    date:        Tue Nov 29 20:06:47 2016 +0100
    summary:     Synchro clément

    changeset:   15:55a752433466
    branch:      LBI-b1
    parent:      14:30eecae0f9ae
    parent:      12:2978f73be137
    user:        Clement <jdoe@example.com>
    date:        Tue Nov 29 20:22:13 2016 +0100
    summary:     Synchro Depot

    changeset:   16:07ab5abdf0ff
    branch:      LBI-b1
    user:        Clement <jdoe@example.com>
    date:        Tue Nov 29 20:24:15 2016 +0100
    summary:     Ajout Licence

    changeset:   17:015a72ef82cf
    branch:      LBI-b1
    tag:         tip
    user:        Clement <jdoe@example.com>
    date:        Tue Nov 29 20:27:03 2016 +0100
    summary:     Ajout ROADMAP

ETAPE 39
---------

.. code-block:: sh

    <Julien>~/TP-VCS/Julien $ hg pull
    <Julien>~/TP-VCS/Julien $ hg rebase

hg rebase diffère par son effet sur l'historique. Là où hg merge fusionne en conservant les anciens changement, rebase lui fusionne mais remplace les anciens changements. Il les remplace.

Dans .hg/hgrc
.. code-block:: sh

    [extensions]
    rebase=

.. code-block:: sh

    <Julien>~/TP-VCS/Julien $ hg commit -m "Modif Rapport"
    <Julien>~/TP-VCS/Julien $ hg rebase
    rebasing 14:b5346f424544 "Ajout notes"
    merging rapport.rst
    rebasing 19:cadb599a7567 "Modif Rapport" (tip)
    merging rapport.rst
    saved backup bundle to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/Julien/.hg/strip-backup/b5346f424544-c224bfea-backup.hg

ETAPE 40
---------

.. code-block:: sh

    <Julien>~/TP-VCS/Julien $ hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 2 changesets with 2 changes to 1 files

ETAPE 41
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg pull
    pulling from /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 2 changesets with 2 changes to 1 files
    (run 'hg update' to get a working copy)
    <Clement>~/TP-VCS/Clement $ hg update
    1 files updated, 0 files merged, 0 files removed, 0 files unresolved
    <Clement>~/TP-VCS/Clement $ hg update LBI-b1
    0 files updated, 0 files merged, 0 files removed, 0 files unresolved

    <Clement>~/TP-VCS/Clement $ mkdir src
    <Clement>~/TP-VCS/Clement $ mv rapport.rst src/
    <Clement>~/TP-VCS/Clement $ mv Makefile src/
    <Clement>~/TP-VCS/Clement $ hg status
    ! Makefile
    ! rapport.rst
    ? src/Makefile
    ? src/rapport.rst
    <Clement>~/TP-VCS/Clement $ hg add src/Makefile 
    <Clement>~/TP-VCS/Clement $ hg add src/rapport.rst 
    <Clement>~/TP-VCS/Clement $ hg status
    A src/Makefile
    A src/rapport.rst
    ! Makefile
    ! rapport.rst
    <Clement>~/TP-VCS/Clement $ hg commit -m "Move to sr"
    <Clement>~/TP-VCS/Clement $ hg remove --after
    suppression de Makefile
    suppression de rapport.rst
    not removing .hgignore: file still exists
    not removing AUTHORS.txt: file still exists
    not removing LICENSE.txt: file still exists
    not removing README.rst: file still exists
    not removing ROADMAP.txt: file still exists
    not removing src/Makefile: file still exists
    not removing src/rapport.rst: file still exists



ETAPE 42
---------

.. code-block:: sh
    
    <Clement>~/TP-VCS/Clement $ hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 1 changesets with 2 changes to 2 files



ETAPE 43
---------

.. code-block:: sh

    <Julien>~/TP-VCS/Julien $ hg pull
    pulling from /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 1 changesets with 2 changes to 2 files
    (run 'hg update' to get a working copy)
    <Julien>~/TP-VCS/Julien $ hg update
    2 files updated, 0 files merged, 0 files removed, 0 files unresolved
    <Julien>~/TP-VCS/Julien $ hg update LBI-b1
    0 files updated, 0 files merged, 0 files removed, 0 files unresolved
    <Julien>~/TP-VCS/Julien $ hg update
    local [working copy] changed rapport.rst which other [destination] deleted
    use (c)hanged version, (d)elete, or leave (u)nresolved? d
    0 files updated, 0 files merged, 2 files removed, 0 files unresolved


ETAPE 44
---------

.. code-block:: sh

    <Julien>~/TP-VCS/Julien $ hg log src/rapport.rst

ETAPE 45
---------

.. code-block:: sh

    <Aurelie>~/TP-VCS/Aurelie $ hg pull
    pulling from /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 17 changesets with 17 changes to 6 files (+1 heads)
    (run 'hg heads' to see heads)
    <Aurelie>~/TP-VCS/Aurelie $ hg update
    5 files updated, 0 files merged, 2 files removed, 0 files unresolved
    <Aurelie>~/TP-VCS/Aurelie $ hg update LBI-b1
    0 files updated, 0 files merged, 0 files removed, 0 files unresolved
    <Aurelie>~/TP-VCS/Aurelie $ hg tag LBI-v1.0

ETAPE 46
---------

.. code-block:: sh

    <Aurelie>~/TP-VCS/Aurelie $ hg branch LBI-b2
    marked working directory as branch LBI-b2
    <Aurelie>~/TP-VCS/Aurelie $ hg commit -m "New branch"
    <Aurelie>~/TP-VCS/Aurelie $ hg push --new-branch
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 2 changesets with 1 changes to 1 files

ETAPE 47
---------

.. code-block:: sh

     <Clement>~/TP-VCS/Clement $ hg pull
    pulling from /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 2 changesets with 1 changes to 1 files
    (run 'hg update' to get a working copy)
    <Clement>~/TP-VCS/Clement $ hg update
    1 files updated, 0 files merged, 0 files removed, 0 files unresolved
    <Clement>~/TP-VCS/Clement $ hg update LBI-b2
    0 files updated, 0 files merged, 0 files removed, 0 files unresolved

ETAPE 48
---------

.. code-block:: sh

     <Clement>~/TP-VCS/Clement $ hg commit -m "MàJ Notes"
     <Clement>~/TP-VCS/Clement $ hg push


ETAPE 49
---------

.. code-block:: sh

    <Julien>~/TP-VCS/Julien $ hg pull
    <Julien>~/TP-VCS/Julien $ hg update
    <Julien>~/TP-VCS/Julien $ hg update LBI-b2

ETAPE 50
---------

.. code-block:: sh

     <Julien>~/TP-VCS/Julien $ hg commit -m "MàJ Notes"
     <Julien>~/TP-VCS/Julien $ hg push

ETAPE 51
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg pull
    <Clement>~/TP-VCS/Clement $ hg update
    <Clement>~/TP-VCS/Clement $ hg update LBI-b1 

ETAPE 52
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg commit -m "Correction Notes"
    <Clement>~/TP-VCS/Clement $ hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 1 changesets with 1 changes to 1 files (+1 heads)

ETAPE 53
---------

.. code-block:: sh

    <Clement>~/TP-VCS/Clement $ hg diff -r LBI-v1.0

ETAPE 54
---------

.. code-block:: sh

    <Aurélie>~/TP-VCS/Aurélie $ hg pull
    <Aurélie>~/TP-VCS/Aurélie $ hg update
    <Aurélie>~/TP-VCS/Aurélie $ hg update LBI-b1
    <Aurélie>~/TP-VCS/Aurélie $ hg tag LBI-v1.1

ETAPE 55
---------

.. code-block:: sh

    <Aurélie>~/TP-VCS/Aurélie $ hg merge LBI-b2
    merging src/rapport.rst
    3 fichiers à éditer
     output file src/rapport.rst appears unchanged
    was merge successful (yn)? y
    0 files updated, 1 files merged, 0 files removed, 0 files unresolved
    (branch merge, don't forget to commit)
    <Aurélie>~/TP-VCS/Aurélie hg commit -m "Merge on LBI-b2"
    <Aurélie>~/TP-VCS/Aurélie hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 2 changesets with 2 changes to 2 files (-1 heads)

ETAPE 56
---------

.. code-block:: sh

    <Aurélie>~/TP-VCS/Aurélie $ hg log -f #(Historique Complet)
    <Aurélie>~/TP-VCS/Aurélie $ hg log --graph #(Graphe)
    <Aurélie>~/TP-VCS/Aurélie $ hg log --stat #(Résumé)
    <Aurélie>~/TP-VCS/Aurélie $ hg log -v #(Fichiers)

ETAPE 57
---------

.. code-block:: sh

    <Loic>~/TP-VCS/ $ mkdir Loic
    <Loic>~/TP-VCS/ $ hg clone ref Loic
    <Loic>~/TP-VCS/ $ hg update LBI-b2

ETAPE 58
---------

.. code-block:: sh
    
    <Loic>~/TP-VCS/ cd Loic
    <Loic>~/TP-VCS/Loic $ vi .hg/hgrc

ETAPE 59
---------

.. code-block:: sh

    <Loic>~/TP-VCS/Loic $ vi .hg/hgrc

ETAPE 60
---------

.. code-block:: sh

    <Loic>~/TP-VCS/Loic $ vi src/rapport.rst

ETAPE 61
---------

.. code-block:: sh

    <Loic>~/TP-VCS/Loic $ hg export  > ../Aurelie/loic.patch

ETAPE 62
--------

.. code-block:: sh

    <Aurelie>~/TP-VCS/Aurelie $ hg update LBI-b2
    <Aurelie>~/TP-VCS/Aurelie $ hg import loic.patch
    <Aurelie>~/TP-VCS/Aurelie $ hg commit -m "Modifs"
    <Aurelie>~/TP-VCS/Aurelie $ hg push

ETAPE 63
--------

.. code-block:: sh

    <Loic>~/TP-VCS/Loic $ vi .hg/hgrc
    <Loic>~/TP-VCS/Loic $ hg pull
    <Loic>~/TP-VCS/Loic hg update 

ETAPE 64
--------

.. code-block:: sh

    <Aurelie>~/TP-VCS/Aurelie $ hg commit -m "Finalisation rapport"
    <Aurelie>~/TP-VCS/Aurelie $ hg push
    pushing to /home/pingwin/Documents/CNAM_2016_2017/OPI/TP1/EnvTP1/TP_VCS/ref
    searching for changes
    adding changesets
    adding manifests
    adding file changes
    added 1 changesets with 1 changes to 1 files
    <Aurelie>~/TP-VCS/Aurelie $ cd src
    <Aurelie>~/TP-VCS/Aurelie/src make 