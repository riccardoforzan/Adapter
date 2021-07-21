# Adapter

Nell'implementazione della soluzione richiesta mi sono avvalso di 2 pattern:

Il primo pattern è l'Adapter pattern della Gang Of Four.
Questo pattern si presta ad essere utilizzato nella soluzione in quanto l'interfaccia target, richiesta dal client
ovvero dalle classi scritte per J2SE 1.4.2 devono essere adattate a funzionare in ambiente J2ME CLDC 1.1.
L'intetno nell'applicazione di questo pattern è quello di convertire l'interfaccia attesa dalle classi con
quanto messo a disposizione da J2ME CLDC 1.1.
Le implementazioni delle classi Adapter è stata fatta mediante l'utilizzo di Object Adapter.
Ho fatto questa scelta implementativa perchè l'utilizzo dell'object adapter permette di estendere le classi
adapter, cosa impossibile utilizzando un Class Adapter in quanto questo estende l'Adaptee.

Il secondo pattern che ho utilizzato è l'Iterator Pattern.
L'iteratore fornisce una via di accesso uniforme alle collezioni su cui lavora senza mostrarne i dettagli implementativi.
L'utilizzo di questo pattern si è rivelato particolarmente utile nella scrittura sia delle classi Adapter, ha permesso la 
creazione di alcune classi astratte che implementassero comportamenti comuni, aumentando così il riutilizzo del codice nei test.

Nella consegna sono inclusi:
* La cartella JavaDoc contenente tutta la documentazione generata dall'omonimo tool
* Un file testDoc in formato HTML contenente tutta la specifica della documentazione dei test secondo la specifica SAFe, questo file è stato generato in maniera automatica da un tool da me creato [DocGen](https://github.com/riccardoforzan/DocGen) che permette la creazione automatica di una pagina di sunto riguardante i test se questi rispettano una certa specifica
stabilita.

* Il file Adapter.jar che contiene al suo interno il codice sorgente.

Può essere mandata in esecuzione la main class attraverso il comando java -jar Adapter.jar
