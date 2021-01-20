## TITOLO
# MYWEATHER REST API

## AUTORI

### CRISTIANO VAGNONI 50%
### MARCO ROMANELLI 50%




## INTRODUZIONE
MyWeatherApplication gestisce i dati, restituiti dalla chiamata all'API Current weather data di OpenWeather, delle citta all'interno di un box definito da coordinate geografiche.  

## DESCRIZIONE ED UTILIZZO

L'esecuzione prevede la proposta di diverse funzionalità, tramite utilizzo di rotte (Paths) , da parte dell'applicazione per l'utente, il quale si occupa di assegnare i valori alle keys.

Le KEYS a cui assegnare i VALUES sono:

{
- box
- periodo
- lon-left
- lat-bottom
- lon-right
- lat-top

}


### KEYS

1. Il valore da assegnare alla key " box " dovrà essere uno tra " box1 ", " box2 ", " box3 "; questi valori rappresentano dei box preimpostati (secondo latitudine  e longitudine  in ordine crescente) di città limitrofe di Ancona.   

Le città corrispondenti ai box predefiniti sono:

{

* box1: "Ancona,Osimo,Castelfidardo,Iesi,Recanati,Senigallia,Fano"
* box2: "Falconara Marittima,Ancona,Osimo,Castelfidardo,Iesi,Recanati,Senigallia,Macerata,Civitanova Marche,Fano,Sant'Elpidio a Mare,Porto Sant'Elpidio,Fabriano,Pesaro"
* box3: "Falconara Marittima,Ancona,Osimo,Castelfidardo,Iesi,Recanati,Senigallia,Fano,Macerata,Pesaro,Fabriano,Tolentino,Civitanova Marche,Urbino,Sant'Elpidio a Mare,Porto Sant'Elpidio,Cattolica,Gualdo Tadino,Fermo,Porto San Giorgio,Riccione"

}

Nel caso in cui non venga attribuito il valore a "box", l'applicazione si servirà del valore di default (defaultValue) che è settato a "box1".

![paramDefault](https://user-images.githubusercontent.com/38563627/104968655-c0631080-59e6-11eb-88e6-95861dbe2c5f.PNG)



2. La key " periodo " prende come valore un numero intero che rappresenta lo storico (il numero di giorni addietro).
Nel caso in cui non venga attribuito il valore a "periodo", l'applicazione si servirà del valore di default (defaultValue) che è settato a "1".

3. Assegnamo alle keys " lon-left "," lat-bottom "," lon-right "," lat-top " come valore un numero decimale corrispondente alla latitudine e alla longitudine scelte dall'utente;  queste definiscono il box.
Nel caso in cui non venga attribuito il valore a " lon-left "," lat-bottom "," lon-right "," lat-top ", l'applicazione si servirà del valore di default (defaultValue) che è così settato:

* Queste coordinate sono le stesse impostate di "box1"

![defaultValuerettangolo](https://user-images.githubusercontent.com/38563627/104968831-34051d80-59e7-11eb-98a6-48bc3a2103b0.PNG)



 
### PATHS

L'applicazione viene fatta partire sul " localhost:8080 " e tramite le paths l'utente effettua le sue richieste

* localhost:8080



![localHost8080](https://user-images.githubusercontent.com/38563627/104964098-14b4c300-59dc-11eb-86d5-a0e2f92860aa.PNG)



* (/home) 
Mostra i path che l'utente ha a disposizione:

![mostrapath](https://user-images.githubusercontent.com/54573842/105088492-3ffee700-5a9c-11eb-899a-d0ffb4b9409e.PNG)





1. ("/attuale")


Tipo | Path | 
---- | ---- | 
GET | localhost:8080/attuale|


![attuale](https://user-images.githubusercontent.com/38563627/104964242-52b1e700-59dc-11eb-8989-e4273667accb.PNG)



Rotta di GET che mostra i dati attuali della temperatura media, temperatura massima, temperatura minima ed escursione; tramite la key "box" l'utente può scegliere tra i vari box delle città confinanti con Ancona.
Restituisce i nomi delle città con la temperatura più alta e la citta con la temperatura più bassa.

![attualebox1](https://user-images.githubusercontent.com/38563627/104964690-3498b680-59dd-11eb-9a29-dcf122f688e2.PNG)


* Risposta all'utente rispetto al box dell'immagine sopra

![attualebox1exit](https://user-images.githubusercontent.com/38563627/104965357-de2c7780-59de-11eb-921b-9ba8b5414368.PNG)


* Citta con temperatura massima più alta e minima più bassa

![tempmax+alta](https://user-images.githubusercontent.com/38563627/104967809-91e43600-59e4-11eb-8ec1-fefe7cfb3382.PNG)




2. ("/attuale/rettangolo")


Tipo | Path | 
---- | ---- | 
GET | localhost:8080/attuale/rettangolo |



Rotta di tipo GET che mostra i dati attuali della temperatura media, temperatura massima, temperatura minima ed escursione dove l'utente può scegliere manualmente il box da selezionare mediante l'assegnazione dei valori alle keys "lon-left","lat-bottom","lon-right","lat-top". 


![attualeRettangoloexit](https://user-images.githubusercontent.com/38563627/104965882-ef29b880-59df-11eb-8e57-fdf4c67c6c46.PNG)


#### NOTA:
Nel caso i cui il valore assegnato a "lon-left" è maggiore di qello assegnato a "lon-right" o il valore assegnato a "lat-bottom" è maggiore di qello assegnato a "lat-top", i parametri non saranno validi.




3. ("/archivioBox")


Tipo | Path | 
---- | ---- | 
GET | localhost:8080/archivioBox |



Rotta di tipo GET che mostra i dati degli archivi delle citta riguardo alla temperatura media, temperatura massima, temperatura minima ed escursione ; le keys a cui assegnare i valori sono "periodo" e "box".
Viene restituita inoltre la città con la temperatura massima più alta, la città con temperatura minima più bassa.



![archivioBoxperiodo2box1](https://user-images.githubusercontent.com/38563627/104963707-6ad53680-59db-11eb-9f4b-cadbd0c59652.PNG)




4. ("/filtraCitta")


Tipo | Path | 
---- | ---- | 
POST | localhost:8080/filtraCitta |

Rotta di tipo POST.


Viene inserito un "Params" box così che l'utente può vedere le città di quel box con i relativi dati storici della temperatura media,temperatura massima, temperatura minima ed escursione; di default il box viene settato a "box1".

Mediante un body restituisce all'utente i dati degli archivi della temperatura media, temperatura massima, temperatura minima ed escursione di una singola città per il periodo valutato.

Viene restituita la temperatura massima più alta,la temperatura minima più bassa e la varianza maggiore della temperatura media della città scelta, del periodo stabilito.


* Un esempio possibile di BODY da inserire è questa:

![BODY2](https://user-images.githubusercontent.com/54573842/105070048-04a3ee80-5a83-11eb-854f-569beb187666.PNG)


* Risposta: 

![answerPOST](https://user-images.githubusercontent.com/54573842/105070268-4f256b00-5a83-11eb-9f82-9df6d4fac008.PNG)




## UML

### CASI D'USO


* UTENTE :

![case use utente](https://user-images.githubusercontent.com/54573842/105167726-5ac37100-5b19-11eb-87a0-fa56c13630d5.jpeg)


* USERSYSTEM :

![case use usersystem](https://user-images.githubusercontent.com/54573842/105167789-73cc2200-5b19-11eb-9b65-b0ad844ce5c4.jpeg)




### DIAGRAMMA DELLE CLASSI



* CONTROLLER :

![class diagram controller](https://user-images.githubusercontent.com/54573842/105167953-b42ba000-5b19-11eb-9125-34b7f3b3f5d9.jpeg)


* SERVICE : 

![class diagram serviceApi](https://user-images.githubusercontent.com/54573842/105168043-d58c8c00-5b19-11eb-9b50-29fb7078d138.jpeg)


* UTILITIES :

![class diagram utilities](https://user-images.githubusercontent.com/54573842/105168148-ff45b300-5b19-11eb-9c2c-93a348626e00.jpeg)


* MODEL : 

![class diagram model](https://user-images.githubusercontent.com/54573842/105168215-1be1eb00-5b1a-11eb-86d4-541ace5fbec1.jpeg)


* FILTRI : 

![class diagram Filtri](https://user-images.githubusercontent.com/54573842/105168282-36b45f80-5b1a-11eb-926c-0321cde02828.jpeg)


* EXCEPTION : 

![class diagram exception](https://user-images.githubusercontent.com/54573842/105168366-5186d400-5b1a-11eb-938b-ffc02e536797.jpeg)



### DIAGRAMMA DELLE SEQUENZE



* ATTUALE :

![Sequence Diagram attuale](https://user-images.githubusercontent.com/54573842/105168462-754a1a00-5b1a-11eb-93d8-cbc13f085394.png)


* STORICO :

![Sequence Diagram archivioBox](https://user-images.githubusercontent.com/54573842/105168607-a296c800-5b1a-11eb-8bfa-5a391e382c7b.png)


* FILTRATO :

![Sequence Diagram archivioBox](https://user-images.githubusercontent.com/54573842/105168653-b5110180-5b1a-11eb-855c-4acc4d5dab7b.png)








#### readMe scritto tramite MARKDOWN
