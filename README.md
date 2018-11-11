Készítette: Berecz Ádám és Bossányi Máté
# Feladat leírása
- A cél egy webes alkalmazás elkészítése, ahol a felhasználóknak regisztráció/bejelentkezés után lehetőségük van egy 2D-s shooter játékot játszaniuk
- A bejelentkezett felhasználóknak ezen kívül lehetőségük van üzenetek küldésére, fogadására, profiljuk szerkesztésére, korábbi játékaik eredményének megtekintésére
# Szerepkörök
### Vendég
 - Toplista
 - Regisztráció / bejelentkezés
### Felhasználó
 - Játék indítása
 - Üzenetek küldése és fogadása
 - Saját profil megtekintése, jármű választása
 - Korábbi játékai eredményének megtekintése
### Admin
 - Összes felhasználó megtekintése
 - Összes profil megtekintése
 - Összes korábbi játék megtekintése
 - Felhasználó törlése, jogosultságának módosítása
# Nem funkcionális követelmények
- Felhasználóbarát, könnyen áttekinthető elrendezés és kinézet
- Gyors működés
- Biztonságos működés
# Funkcionális követelmények
### Adatbázis

| Kapcsolatok |
| ------------- |
| *MESSAGES **-N--1-** USER* |
| *SCORES **-N--1-** USER*  |
| *UNLOCKED **-N--1-** USER* |
| *VEHICLES **-N--N-** USER* |
| *VEHICLES **-1--N-** UNLOCKED* |

![alt text](https://github.com/Flash97111/alkfejl/blob/master/img/db.png)
**USER**

| Oszlop | Tipus | Leiras |
| ------------- | ------------- | ------------- |
| ID  | int  | elsődleges kulcs |
| USERNAME  | varchar | felhasználónév |
| PASSWORD  | varchar | jelszó |
| ROLE  | enum | ROLE_USER vagy ROLE_ADMIN |
| HIGHSCORE  | int | legnagyobb elért pontszám |

**VEHICLES**

| Oszlop | Tipus | Leiras |
| ------------- | ------------- | ------------- |
| ID  | int  | elsődleges kulcs |
| NAME  | varchar | jármű neve |
| SCORE  | int | feloldáshoz szükséges pontszám |

**UNLOCKED**

| Oszlop | Tipus | Leiras |
| ------------- | ------------- | ------------- |
| ID  | int  | elsődleges kulcs |
| USER_ID  | int | felhasználó ID-ja |
| VEHICLE_ID | int | jármű ID-ja |
| DATE | date | feloldás ideje |

**SCORES**

| Oszlop | Tipus | Leiras |
| ------------- | ------------- | ------------- |
| ID  | int  | elsődleges kulcs |
| USER_ID  | int  | felhasználó ID-ja |
| SCORE | int | elért pontszám |
| DATE | date | játék ideje |


**MESSAGES**


| Oszlop | Tipus | Leiras |
| ------------- | ------------- | ------------- |
| ID  | int  | elsődleges kulcs |
| SENDER_ID  | int | küldő ID-ja |
| RECEIVER_ID | int | címzett ID-ja |
| TITLE | varchar | üzenet tárgya |
| MESSAGE | varchar | üzenet szövege |
| OPENED | boolean | elolvasta-e a címzett |
| DATE | date | levélírás ideje |

### Használati esetek
![alt text](https://github.com/Flash97111/alkfejl/blob/master/img/usecase.png)
### Végpontok
***ROLE_GUEST***

| Metódus | Request | Leírás |
| ------------- | ------------- | ------------- |
| GET  | / | Főoldal |
| GET  | /register | Regisztációs felület |
| GET  | /toplist  | Toplista |
| POST  | /login | Bejelentkezési adatok küldése |
| POST | /register | Regisztrációs adatok küldése |

***ROLE_USER***

| Metódus | Request | Leírás |
| ------------- | ------------- | ------------- |
| GET  | my/messages | Üzenetek |
| GET  | my/messages/new | Üzenetküldő felület |
| GET  | my/profile | Profil |
| GET  | my/games  | Korábbi játékok |
| POST  | my/messages/new | Új üzenet adatainak küldése |

***ROLE_ADMIN***

| Metódus | Request | Leírás |
| ------------- | ------------- | ------------- |
| GET  | /users | Felhasználók |
| GET  | /users/:ID: | Adott ID-jú felhasználó |
| GET  | /games| Játékosok játékai |
| GET  | /games/:ID: | Adott ID-jú felhasználó játékai |
| GET  | /profile | Profilok |
| GET  | /profile/:ID: | Adott ID-jú felhasználó profilje |
| PATCH  | /users/:ID: | Adott ID-jú felhasználó jogosultságának változtatása |
| DELETE  | /users/:ID: | Felhasználó törlése |
