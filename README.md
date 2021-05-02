# KnotsNCrosses

KnotsNCrosses er en app som lar deg spille tre på rad over internett. Appen bruker et sett med API'er tildelt av foreleser. API'et gir deg muligheten til å spille mot alle andre som bruker samme API, samme app er ikke nødvendig. Appen jeg har laget har muligheten til å spille så mange spill du vil samtidig, du kan lage dine egne spill eller bli med i andres spill gitt at du får gameId'en deres.

Første gang man starter appen vil man få opp en skjerm for å lage et brukernavn. Navnet blir lagret på Firebase slik at man ikke trenger å lage et nytt navn hver gang man vil spille.

Under er noen bilder av hvordan appen er i bruk.

![Welcome Screen image :)](https://github.com/thomaseho/KnotsNCrosses/blob/main/images/WelcomeScreen.png)

Det første som møter deg når du åpner appen er en velkomstskjerm, hvis det er første gang du åpner appen vil du bli tatt videre for å lage et brukernavn, andre gangen blir du tatt med til menyen over spill.

![Menu Screen image :)](https://github.com/thomaseho/KnotsNCrosses/blob/main/images/MenuScreen.png)

Denne skjermen viser litt forskjellige ting, øverst til venstre blir navnet ditt presentert, øverst til høyre vil være det nyligste spillet du lagde sin Id.

Hoveddelen av skjermen viser et ScrollView med alle spillene du er en del av, Spiller 1 blir satt øverst i hvert kort, mens spiller 2 blir satt nederst. Hvert kort har også presentert Id'en sin for å lett kunne vise til hva andre skal ha for å bli med.

Aktive spill blir ikke lagret på Firebase, men er noe jeg ville gjort ved fremtidig utvikling av appen. Jeg ville også lagt inn muligheten til å fjerne spill, eller starte et nytt et hvis spillet er over. Slik som det er nå må man lage nye spill når man åpner appen, men så er det tre på rad, og spillet varer ikke veldig lenge.(Kanskje det er for alles beste at det ikke blir lagret?)

Til sist et bilde av hvordan selve spillskjermen ser ut, øverst til venstre vises spiller 1, øverst til høyre spiller 2. Selve griden viser X og O for respektivt for spiller 1 og 2. Om man eventuelt skulle vinne vil det også vises en vinnertekst men hvilken spiller som vant i bunnen av skjermen.

![Game Screen image :)](https://github.com/thomaseho/KnotsNCrosses/blob/main/images/GameScreen.png)
