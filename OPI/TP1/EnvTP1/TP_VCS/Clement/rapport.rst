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
