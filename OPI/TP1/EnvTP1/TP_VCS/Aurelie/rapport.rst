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

    <Aurelie>~/TP-VCS $ vi Aurelie/.hg/.hgrc

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

    <Clement>~/TP-VCS $ vi Clement/.hg/.hgrc

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
