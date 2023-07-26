A játék konzolosan lett megvalósítva és kipróbálva IntelliJ IDEA-ban java 17-es verzióval.

IDEA-ban hozz létre egy új projektet jobb klikk src mappára-> Open in Explorer és a zipben található
elemeket vágd ki és helyezd be az alábbi helyre és már látni is fogod a programot amit
az units mappában lévő Main classból lehet elindítani.
Az elején a nehézségi szint kiválasztása után, megkérdezi tőled a program, hogy mit/miket szeretnél vásárolni.
Itt történik az egységek/varázslatok/tulajdonságpontok vásárlása.
(varázslatok vásárlásánál az ár mellett lévő érték mutatja hogy mennyi mannába kerül a varázslat használata)
Továbbá meg tudod nézni a hős tulajdonságait és mindezek után
tovább léphetsz a csata előtti fázisba, ahol az egységeidet el tudod helyezni a pályán x és y koordináták megadásával.


Rövidítések:
(P) - játékos által irányított egységek
(C) - gép által irányított egységek
 F  - farmer(földműves)
 A  - archer(Íjász)
Gri - griff
 H  - Hogs(4. egység)
 P  - Pekka(5. egység)
 
Tájékozódás:
spells mappa: varázslatok létrehozása és kezelése
units mappa: egységek létrehozása és kezelése
Army: hadsereg inicializálása
Cell: mezők létrehozása
Hero: hős létrehozása és kezelése
Main: program kezelése és futtatása




Segítség egységek elhelyezéséhez:

        0      1      2      3      4      5      6      7      8      9     10     11
    -------------------------------------------------------------------------------------
0   |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |
   
    -------------------------------------------------------------------------------------
1   |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |
   
    -------------------------------------------------------------------------------------
2   |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |

    -------------------------------------------------------------------------------------
3   |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |

    -------------------------------------------------------------------------------------
4   |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |

    -------------------------------------------------------------------------------------
5   |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |
   
    -------------------------------------------------------------------------------------
6   |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |
   
    -------------------------------------------------------------------------------------
7   |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |
   
    -------------------------------------------------------------------------------------
8   |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |
   
    -------------------------------------------------------------------------------------
9   |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |   .  |
   
    -------------------------------------------------------------------------------------