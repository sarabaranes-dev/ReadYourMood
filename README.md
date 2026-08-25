# 📚 ReadYourMood

ReadYourMood è una piattaforma innovativa per la gestione, la recensione e l'acquisto di libri, con una caratteristica speciale: ti aiuta a trovare il libro perfetto in base alla tua **emozione** attuale. 

Il progetto è composto da un solido backend in Spring Boot e da un'interfaccia utente moderna e reattiva sviluppata in React.

## ✨ Features
- **Ricerca Emotiva**: Trova o categorizza i libri in base alle emozioni (`EmozioneLibro`).
- **Marketplace/Libreria**: Gestione dello stato dei libri (`CondizioneLibro`, `StatoLibro`) e degli ordini degli utenti (`Ordine`, `StatoOrdine`).
- **Sistema di Recensioni**: Gli utenti possono lasciare feedback e recensioni sui libri letti (`Recensione`).
- **Gestione Utenti**: Registrazione, autenticazione e autorizzazione (Admin/User) tramite Spring Security e JWT/Sessioni.
- **Upload Immagini**: Sistema integrato per il caricamento delle copertine dei libri (fino a 5MB).

## 🛠 Tech Stack

### Backend (`siw-libro-01`)
- **Java 17**
- **Spring Boot 3** (Web, Data JPA, Security, Validation)
- **Thymeleaf** (Usato per alcune viste di backoffice o legacy)
- **PostgreSQL** (Database Relazionale)
- **Springdoc OpenAPI (Swagger)** (Documentazione API automatica)

### Frontend (`siw-libri-react`)
- **React 19**
- **TypeScript**
- **Vite** (Build tool e dev server ultra-veloce)
- **Material UI (MUI)** (Libreria di componenti React per il design)
- **Axios** (Client HTTP per l'integrazione con le API backend)

## 🏗 Architecture
L'applicazione adotta una classica **Architettura Layered (a strati)** nel backend, che separa le responsabilità e facilita la manutenzione:
- **`controller`**: Gestisce le richieste REST (dal frontend React) o MVC (per Thymeleaf).
- **`service`**: Incapsula tutta la logica di business (gestione ordini, associazione emozioni-libri).
- **`repository`**: Livello di persistenza tramite Spring Data JPA.
- **`model`**: Entità JPA (`Libro`, `Ordine`, `Utente`, `Recensione`, ecc.) mappate direttamente sulle tabelle PostgreSQL.
- **`authentication`**: Classi dedicate alla sicurezza e al login (Spring Security).

## 🚀 Setup & Installation

### Prerequisiti
- **Java 17+**
- **Node.js 18+**
- **PostgreSQL** installato e in esecuzione (porta predefinita 5432)

### 1. Clonare il repository
```bash
git clone https://github.com/tuo-username/ReadYourMood.git
cd ReadYourMood
```

### 2. Configurare il Database
Crea un database PostgreSQL locale:
```sql
CREATE DATABASE books;
```
Aggiorna le credenziali configurate nel file `application.properties` (cartella backend):
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/books
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Avviare il Backend
Spostati nella cartella del backend e avvia l'applicazione Spring Boot:
```bash
cd siw-libro-01
./mvnw spring-boot:run
```
L'API sarà esposta sulla porta `8081` (`http://localhost:8081`).

### 4. Avviare il Frontend
Apri una nuova finestra del terminale, entra nella cartella del frontend e avvia Vite:
```bash
cd siw-libri-react
npm install
npm run dev
```
Il frontend sarà visibile all'URL fornito da Vite nel terminale (tipicamente `http://localhost:5173`).

## 💡 Usage (Esempio di flusso)
1. **Registrazione**: Un nuovo utente si registra alla piattaforma.
2. **Esplorazione**: L'utente naviga il catalogo, filtrando i libri in base all'umore (es. "Rilassante", "Avvincente").
3. **Ordine**: L'utente seleziona un libro e procede con l'ordine. Lo stato dell'ordine cambia da "IN_LAVORAZIONE" a "SPEDITO" (gestito dagli admin).
4. **Recensione**: Dopo la lettura, l'utente lascia una recensione e vota l'emozione suscitata dal libro.

## 🔮 Future Improvements
- **Dockerizzazione**: Aggiungere `Dockerfile` e `docker-compose.yml` per un setup istantaneo dell'ambiente di sviluppo.
- **Integrazione API Esterne**: Integrare Google Books API o OpenLibrary per l'autocompletamento delle info sui libri (ISBN, trama).
- **Dashboard Admin React**: Migrare completamente tutte le viste admin da Thymeleaf a React.
- **Sistema di Raccomandazione**: Usare un algoritmo basato sui tag emotivi per consigliare libri simili all'utente.

