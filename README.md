Ciao, queste sono le istruzioni del progetto Diario. 

Seguendo il path "Diario\Backend\Diario\src\main\java" troverai tutto il codice del progetto.
Nella cartella "Diario\Backend\Diario" troverai l'analisi tecnica rinominata "Diario_Ate".
Tutti i file per il frontend sono al seguente path "Diario\Backend\Diario\src\main\resources".


Segui questi passaggi per provare l'api:

1. Installazione
- Consulta il file word rinominato "Diario_Ate" (Diario\Backend\Diario)  alla sezione "1.2 tecnologie utilizzate" per installare tutte le tecnologie utilizzate nel progetto.
- Importa il progetto su Eclipse come Maven project. Seleziona la directory e inserisci tutte le dipendenze di Spring riportate nella sezione "1.2 tecnologie utilizzate" nel file pom.xml.
- Crea i database con le relative tabelle in PostgreSQL e i documents in MongoDB seguendo la struttura riportata nella sezione "2.3 Classi modello".


2. Configurazione
- Crea le variabili di ambiente {DB_URL_POSTGRES}, ${DB_USERNAME_POSTGRES} ,${DB_PASSWORD_POSTGRES} ${MONGODB_URI} inserendo come valore i dati per collegare i database creati.
- Nella cartella "Programmi" dove sono situati i file di sistema di Postgres, trova il file pg_hba.conf è setta i metodi di configurazione password a "m5" esempio "local   all             all                                md5" 
- Nei file del progetto cerca application.propieties e aggiungi le seguenti stringhe per configurare il collegamento coi i database: 
spring.data.mongodb.uri=${MONGODB_URI}
spring.datasource.url=jdbc:${DB_URL_POSTGRES}
spring.datasource.username=${DB_USERNAME_POSTGRES}
spring.datasource.password=${DB_PASSWORD_POSTGRES}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.platform=postgresql


3. Test Api
- Crea il file jar inserendo il seguente comando "mvn clean package"
 comando sul terminale posizionandoti nella cartella del progetto. Il file jar verrà creato nella cartella target
- Posizionato sulla directory del progetto nel terminale inserire il seguente comando "java -jar nomedelfilejar.jar" per testare l'api
- Accedere ai vari endpoint per usare l'api con il server in funzione
