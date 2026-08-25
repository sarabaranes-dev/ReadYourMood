# 📚 ReadYourMood

[![it](https://img.shields.io/badge/lang-it-green.svg)](#-readyourmood-italiano) 

*(Italian version below / Versione italiana sotto)*

ReadYourMood is an innovative platform for managing, reviewing, and buying books, with a special feature: it helps you find the perfect book based on your current **mood**.

The project consists of a solid backend built with Spring Boot and a modern, responsive user interface developed in React.

## ✨ Features
- **Mood-Based Search**: Find or categorize books based on emotions (`EmozioneLibro`).
- **Marketplace/Library**: Manage book conditions (`CondizioneLibro`, `StatoLibro`) and user orders (`Ordine`, `StatoOrdine`).
- **Review System**: Users can leave feedback and review the books they have read (`Recensione`).
- **User Management**: Registration, authentication, and authorization (Admin/User) via Spring Security and JWT/Sessions.
- **Image Uploads**: Integrated system for uploading book covers (up to 5MB).

## 🛠 Tech Stack

### Backend (`siw-libro-01`)
- **Java 17**
- **Spring Boot 3** (Web, Data JPA, Security, Validation)
- **Thymeleaf** (Used for some backoffice or legacy views)
- **PostgreSQL** (Relational Database)
- **Springdoc OpenAPI (Swagger)** (Automated API documentation)

### Frontend (`siw-libri-react`)
- **React 19**
- **TypeScript**
- **Vite** (Ultra-fast build tool and dev server)
- **Material UI (MUI)** (React component library for design)
- **Axios** (HTTP client for backend API integration)

## 🏗 Architecture
The application adopts a classic **Layered Architecture** in the backend, which separates responsibilities and facilitates maintenance:
- **`controller`**: Handles REST requests (from the React frontend) or MVC requests (for Thymeleaf).
- **`service`**: Encapsulates all business logic (order management, emotion-book associations).
- **`repository`**: Persistence layer via Spring Data JPA.
- **`model`**: JPA entities (`Libro`, `Ordine`, `Utente`, `Recensione`, etc.) directly mapped to PostgreSQL tables.
- **`authentication`**: Classes dedicated to security and login (Spring Security).

## 🚀 Setup & Installation

### Prerequisites
- **Java 17+**
- **Node.js 18+**
- **PostgreSQL** installed and running (default port 5432)

### 1. Clone the repository
```bash
git clone https://github.com/your-username/ReadYourMood.git
cd ReadYourMood
```

### 2. Configure the Database
Create a local PostgreSQL database:
```sql
CREATE DATABASE books;
```
The database credentials can be configured using environment variables. By default, it expects:
- **Username**: `postgres`
- **Password**: `postgres`
*(If your local environment requires different credentials, set the `DB_USERNAME` and `DB_PASSWORD` environment variables before running the application, or update `siw-libro-01/src/main/resources/application.properties` locally)*.

### 3. Start the Backend
Navigate to the backend folder and start the Spring Boot application:
```bash
cd siw-libro-01
./mvnw spring-boot:run
```
The API will be exposed on port `8081` (`http://localhost:8081`).

### 4. Start the Frontend
Open a new terminal window, enter the frontend folder, and start Vite:
```bash
cd siw-libri-react
npm install
npm run dev
```
The frontend will be visible at the URL provided by Vite in the terminal (typically `http://localhost:5173`).

## 💡 Usage (Example Flow)
1. **Registration**: A new user registers on the platform.
2. **Exploration**: The user browses the catalog, filtering books based on mood (e.g., "Relaxing", "Thrilling").
3. **Order**: The user selects a book and proceeds with the order. The order status changes from "IN_PROGRESS" to "SHIPPED" (managed by admins).
4. **Review**: After reading, the user leaves a review and votes on the emotion the book evoked.

## 🔮 Future Improvements
- **Dockerization**: Add a `Dockerfile` and `docker-compose.yml` for instant development environment setup.
- **External API Integration**: Integrate Google Books API or OpenLibrary for auto-completing book info (ISBN, plot).
- **React Admin Dashboard**: Fully migrate all admin views from Thymeleaf to React.
- **Recommendation System**: Use an algorithm based on emotional tags to recommend similar books to the user.

## 📄 License
Distributed under the MIT License. See `LICENSE` for more information.

---
---

# 🇮🇹 ReadYourMood (Italiano)

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
git clone https://github.com/your-username/ReadYourMood.git
cd ReadYourMood
```

### 2. Configurare il Database
Crea un database PostgreSQL locale:
```sql
CREATE DATABASE books;
```
Le credenziali del database possono essere configurate tramite variabili d'ambiente. Di default si aspetta:
- **Username**: `postgres`
- **Password**: `postgres`
*(Se il tuo ambiente locale richiede credenziali diverse, imposta le variabili d'ambiente `DB_USERNAME` e `DB_PASSWORD` prima di avviare l'applicazione, oppure aggiorna `siw-libro-01/src/main/resources/application.properties` in locale)*.

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

## 📄 License
Distribuito sotto la licenza MIT. Vedi `LICENSE` per maggiori informazioni.
