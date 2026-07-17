<p align="center">
  <img src="https://github.com/user-attachments/assets/585136d9-05b5-4832-ad37-0a47d4678433" alt="AirNest Banner" width="720"/>
</p>

<h1 align="center">🏠 AirNest — Hotel Booking & Management Platform</h1>

<p align="center">
  <strong>A production-grade Airbnb-style backend built with Spring Boot 3.5, Java 25, and enterprise design patterns.</strong>
</p>

<p align="center">
  <a href="#-features"><img src="https://img.shields.io/badge/Features-✅-brightgreen?style=for-the-badge" alt="Features"/></a>
  <a href="#-tech-stack"><img src="https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/></a>
  <a href="#-tech-stack"><img src="https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/></a>
  <a href="#-tech-stack"><img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/></a>
  <a href="#-tech-stack"><img src="https://img.shields.io/badge/Stripe-008CDD?style=for-the-badge&logo=stripe&logoColor=white" alt="Stripe"/></a>
  <a href="#-tech-stack"><img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker"/></a>
</p>

<p align="center">
  <a href="https://airnest-api-3bl7.onrender.com/api/v1/swagger-ui/index.html"><img src="https://img.shields.io/badge/🔗_Live_API_Docs-Swagger_UI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger UI"/></a>
  <a href="https://airnest-api-3bl7.onrender.com/api/v1/actuator/health"><img src="https://img.shields.io/badge/❤️_Health_Check-Live-00C853?style=for-the-badge" alt="Health Check"/></a>
</p>

> **🌐 Live API Base URL:** [`https://airnest-api-3bl7.onrender.com/api/v1`](https://airnest-api-3bl7.onrender.com/api/v1/swagger-ui/index.html)
>
> _⚡ First request may take ~30s due to free tier cold start._

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Features](#-features)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [Environment Variables](#-environment-variables)
- [API Reference](#-api-reference)
- [Dynamic Pricing Engine](#-dynamic-pricing-engine)
- [Security](#-security)
- [Database Schema](#-database-schema)
- [Deployment](#-deployment)
- [Contributing](#-contributing)

---

## 🔭 Overview

**AirNest** is a feature-complete hotel booking and management backend that mirrors the core workflows of Airbnb. It supports multi-tenant hotel management, real-time room inventory tracking, a multi-step booking flow with Stripe-powered payments, dynamic pricing strategies, and role-based access control — all exposed via a fully documented RESTful API.

### Why AirNest?

| Capability | Description |
|:--|:--|
| **Multi-step Booking** | Reserve → Add Guests → Pay → Confirm — with 10-minute reservation expiry |
| **Dynamic Pricing** | Decorator-pattern pricing engine (surge, occupancy, urgency, holiday) |
| **Stripe Integration** | Checkout sessions, webhook-driven payment capture, and automated refunds |
| **Role-based Security** | JWT + Spring Security with `GUEST` and `HOTEL_MANAGER` roles |
| **Production Profiles** | Separate `dev` (H2), default, and `prod` (PostgreSQL) configurations |
| **Dockerized** | Multi-stage Docker build for optimized, minimal production images |
| **OpenAPI Docs** | Auto-generated Swagger UI with Bearer token support |

---

## 🏗 Architecture

```
┌──────────────────────────────────────────────────────────────────┐
│                        CLIENT / FRONTEND                        │
└────────────────────────────┬─────────────────────────────────────┘
                             │  HTTPS (REST)
┌────────────────────────────▼─────────────────────────────────────┐
│                     SPRING SECURITY FILTER CHAIN                 │
│            JWT Auth Filter → CORS → CSRF (disabled)              │
└────────────────────────────┬─────────────────────────────────────┘
                             │
┌────────────────────────────▼─────────────────────────────────────┐
│                        CONTROLLER LAYER                          │
│  AuthController · HotelController · HotelBrowseController        │
│  RoomAdminController · InventoryController                       │
│  HotelBookingController · UserController · WebhookController     │
└────────────────────────────┬─────────────────────────────────────┘
                             │
┌────────────────────────────▼─────────────────────────────────────┐
│                         SERVICE LAYER                            │
│  BookingServiceImpl · HotelServiceImpl · RoomServiceImpl         │
│  InventoryServiceImpl · UserServiceImpl · GuestServiceImpl       │
│  CheckoutServiceImpl · PricingUpdateService                      │
└──────┬─────────────────────┬─────────────────────────────────────┘
       │                     │
┌──────▼───────┐  ┌──────────▼──────────────────────────────────┐
│  STRATEGY    │  │           REPOSITORY LAYER (JPA)            │
│  (Pricing)   │  │  BookingRepo · HotelRepo · RoomRepo         │
│  Base → Surge│  │  InventoryRepo · GuestRepo · UserRepo       │
│  → Occupancy │  └──────────────────┬──────────────────────────┘
│  → Urgency   │                     │
│  → Holiday   │        ┌────────────▼────────────┐
└──────────────┘        │   PostgreSQL / H2 (dev) │
                        └─────────────────────────┘
                                     ▲
       ┌─────────────────────────────┘
       │  Webhook
┌──────┴──────┐
│   Stripe    │
│  Payments   │
└─────────────┘
```

---

## 🛠 Tech Stack

| Layer | Technology |
|:--|:--|
| **Language** | Java 25 |
| **Framework** | Spring Boot 3.5.14 |
| **Security** | Spring Security 6 + JWT (jjwt 0.12.6) |
| **ORM** | Spring Data JPA / Hibernate |
| **Database** | PostgreSQL (prod) · H2 (dev/test) |
| **Payments** | Stripe SDK 28.2.0 |
| **API Docs** | SpringDoc OpenAPI 2.8.3 (Swagger UI) |
| **Mapping** | ModelMapper 3.2.2 |
| **Validation** | Jakarta Bean Validation (spring-boot-starter-validation) |
| **Monitoring** | Spring Boot Actuator |
| **Build** | Maven Wrapper |
| **Containerization** | Docker (multi-stage build, Temurin 25) |
| **Boilerplate** | Lombok 1.18.40 |

---

## ✨ Features

### 🔐 Authentication & Authorization
- User signup and login with BCrypt-hashed passwords
- JWT access tokens with refresh token rotation
- Role-based access: `GUEST` (default) and `HOTEL_MANAGER`
- Stateless session management (no server-side sessions)

### 🏨 Hotel Management (Admin)
- Full CRUD for hotels and rooms
- Hotel activation workflow
- Owner-scoped hotel access control
- Revenue and booking reporting with date-range filtering

### 🔍 Hotel Browsing (Public)
- Search hotels by city, check-in/check-out dates, and room count
- Retrieve detailed hotel information with dynamic pricing

### 🗓 Booking Flow
- **Reserve** → **Add Guests** → **Pay** → **Confirm** (multi-step)
- 10-minute reservation expiry with automatic state management
- Pessimistic locking on inventory to prevent overbooking
- Guest management linked to bookings and user profiles

### 💳 Payments (Stripe)
- Stripe Checkout session creation
- Webhook-driven payment capture (`checkout.session.completed`)
- Automated full refunds on booking cancellation

### 💰 Dynamic Pricing Engine
- Strategy + Decorator pattern for composable pricing logic
- **Base** → **Surge** → **Occupancy** → **Urgency** → **Holiday** pipeline
- Per-inventory-date price computation

### 📊 Inventory Management
- Daily room inventory tracking (available vs. booked vs. reserved counts)
- Admin-level inventory updates with pricing overrides
- Atomic operations using JPA `@Modifying` queries

### 👤 User Profiles
- Profile viewing and partial updates
- Guest management (add, update, remove guests)
- Booking history retrieval

---

## 📁 Project Structure

```
src/main/java/com/aman/AirBnb/AirBnb/
├── Advice/                  # Global exception & response handlers
│   ├── ApiError.java
│   ├── ApiResponse.java
│   ├── GlobalExceptionHandler.java
│   └── GlobalResponseHandler.java
├── Config/                  # Application configuration
│   ├── CorsConfig.java
│   ├── MapperConfig.java
│   ├── StripeConfig.java
│   └── SwaggerConfig.java
├── Controller/              # REST controllers (8 controllers)
│   ├── AuthController.java
│   ├── HotelBookingController.java
│   ├── HotelBrowseController.java
│   ├── HotelController.java
│   ├── InventoryController.java
│   ├── RoomAdminController.java
│   ├── UserController.java
│   └── WebhookController.java
├── Dto/                     # Data Transfer Objects (18 DTOs)
├── Entities/                # JPA Entities (8 entities)
│   ├── BookingEntity.java
│   ├── GuestEntity.java
│   ├── HotelContactInfo.java
│   ├── HotelEntity.java
│   ├── HotelMinPriceEntity.java
│   ├── InventoryEntity.java
│   ├── RoomEntity.java
│   └── UserEntity.java
├── Enums/                   # BookingStatus, Gender, PaymentStatus, Role
├── Exceptions/              # Custom exception classes
├── Repositories/            # Spring Data JPA repositories
├── Security/                # JWT auth, filter, and security config
│   ├── AuthService.java
│   ├── JWTAuthFilter.java
│   ├── JWTService.java
│   └── WebSecurityConfig.java
├── Service/                 # Business logic implementations
│   ├── Interfaces/          # Service contracts
│   ├── BookingServiceImpl.java
│   ├── CheckoutServiceImpl.java
│   ├── GuestServiceImpl.java
│   ├── HotelServiceImpl.java
│   ├── InventoryServiceImpl.java
│   ├── PricingUpdateService.java
│   ├── RoomServiceImpl.java
│   └── UserServiceImpl.java
├── Strategy/                # Dynamic pricing (Decorator pattern)
│   ├── PricingStrategy.java         # Interface
│   ├── BasePricingStrategy.java
│   ├── SurgePricingStrategy.java
│   ├── OccupancyPricingStrategy.java
│   ├── UrgencyPricingStrategy.java
│   ├── HolidayPricingStrategy.java
│   └── PricingService.java          # Orchestrator
├── Utils/                   # Utility helpers
└── AirBnbApplication.java  # Entry point
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 25+** (JDK)
- **Maven 3.9+** (or use the included `mvnw` wrapper)
- **PostgreSQL 15+** (for production) — _H2 is used in dev profile_
- **Stripe Account** (for payment integration)

### Quick Start (Dev Mode)

```bash
# Clone the repository
git clone https://github.com/pratyushpanda16/AirNest.git
cd AirNest

# Run with H2 in-memory database (zero config)
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

The API will be available at `http://localhost:8080/api/v1`

**📖 Swagger UI:** `http://localhost:8080/api/v1/swagger-ui/index.html`

**🗄 H2 Console (dev):** `http://localhost:8080/api/v1/h2-console`

### Production Mode

```bash
# Set required environment variables (see below)
export DB_URL=jdbc:postgresql://localhost:5432/airnest
export DB_USERNAME=postgres
export DB_PASSWORD=your_password
export JWT_SECRET=your-256-bit-secret
export FRONTEND_URL=https://your-frontend.com
export STRIPE_SECRET_KEY=sk_live_...
export STRIPE_WEBHOOK_SECRET=whsec_...

# Build and run
./mvnw clean package -DskipTests
java -jar target/AirBnb-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

---

## 🔧 Environment Variables

| Variable | Description | Required |
|:--|:--|:--:|
| `DB_URL` | JDBC connection URL for PostgreSQL | ✅ |
| `DB_USERNAME` | Database username | ✅ |
| `DB_PASSWORD` | Database password | ✅ |
| `JWT_SECRET` | Secret key for JWT signing (min 256-bit) | ✅ |
| `FRONTEND_URL` | Frontend application URL (for CORS & redirects) | ✅ |
| `STRIPE_SECRET_KEY` | Stripe API secret key | ✅ |
| `STRIPE_WEBHOOK_SECRET` | Stripe webhook endpoint secret | ✅ |

> **💡 Tip:** In `dev` profile, all variables are pre-configured with sensible defaults. No setup needed.

---

## 📡 API Reference

Base URL: `/api/v1`

### 🔐 Authentication

| Method | Endpoint | Description | Auth |
|:--:|:--|:--|:--:|
| `POST` | `/auth/signup` | Register a new user | ❌ |
| `POST` | `/auth/login` | Authenticate and receive JWT tokens | ❌ |
| `POST` | `/auth/refresh` | Refresh an expired access token | ❌ |

---

### 🏨 Hotel Management (Admin — `HOTEL_MANAGER` role)

| Method | Endpoint | Description |
|:--:|:--|:--|
| `POST` | `/admin/hotels` | Create a new hotel |
| `GET` | `/admin/hotels` | List all hotels owned by current admin |
| `GET` | `/admin/hotels/{hotelId}` | Get hotel details by ID |
| `PUT` | `/admin/hotels/{hotelId}` | Update hotel details |
| `DELETE` | `/admin/hotels/{hotelId}` | Delete a hotel |
| `PATCH` | `/admin/hotels/{hotelId}/activate` | Activate a hotel (generates inventory) |

---

### 🚪 Room Management (Admin)

| Method | Endpoint | Description |
|:--:|:--|:--|
| `POST` | `/admin/hotels/{hotelId}/rooms` | Create a new room type |
| `GET` | `/admin/hotels/{hotelId}/rooms` | List all rooms for a hotel |
| `GET` | `/admin/hotels/{hotelId}/rooms/{roomId}` | Get room details |
| `PUT` | `/admin/hotels/{hotelId}/rooms/{roomId}` | Update room details |
| `DELETE` | `/admin/hotels/{hotelId}/rooms/{roomId}` | Delete a room |

---

### 📦 Inventory Management (Admin)

| Method | Endpoint | Description |
|:--:|:--|:--|
| `GET` | `/admin/inventory/rooms/{roomId}` | Get inventory for a room |
| `PATCH` | `/admin/inventory/rooms/{roomId}` | Update inventory (price, availability) |

---

### 🔍 Hotel Browsing (Public)

| Method | Endpoint | Description | Auth |
|:--:|:--|:--|:--:|
| `GET` | `/hotels/search` | Search hotels by city, dates, rooms | ❌ |
| `GET` | `/hotels/{hotelId}/info` | Get full hotel info with pricing | ❌ |

---

### 📅 Booking Flow (Authenticated)

| Method | Endpoint | Description |
|:--:|:--|:--|
| `POST` | `/bookings/init` | Initialize a booking (reserves inventory) |
| `POST` | `/bookings/{bookingId}/addGuests` | Add guests to a reservation |
| `POST` | `/bookings/{bookingId}/payments` | Create Stripe checkout session |
| `GET` | `/bookings/{bookingId}/status` | Check current booking status |
| `POST` | `/bookings/{bookingId}/cancel` | Cancel a confirmed booking (triggers refund) |

---

### 📊 Reports (Admin)

| Method | Endpoint | Description |
|:--:|:--|:--|
| `GET` | `/admin/hotels/{hotelId}/bookings` | All bookings for a hotel |
| `GET` | `/admin/hotels/{hotelId}/reports` | Revenue report with date filtering |

---

### 👤 User Profile (Authenticated)

| Method | Endpoint | Description |
|:--:|:--|:--|
| `GET` | `/users/profile` | Get current user's profile |
| `PATCH` | `/users/profile` | Update profile details |
| `GET` | `/users/myBookings` | Get all user's bookings |
| `POST` | `/users/guests` | Add a guest to user's profile |
| `GET` | `/users/guests` | List user's saved guests |
| `PUT` | `/users/guests/{guestId}` | Update a guest |
| `DELETE` | `/users/guests/{guestId}` | Remove a guest |

---

### 🔔 Webhooks

| Method | Endpoint | Description | Auth |
|:--:|:--|:--|:--:|
| `POST` | `/webhook/payment` | Stripe payment event handler | ❌ (Stripe signature) |

---

## 💰 Dynamic Pricing Engine

AirNest uses a **Decorator Pattern** to compose pricing strategies into a flexible, extensible pipeline:

```
BasePricingStrategy
  └── SurgePricingStrategy       (demand-based multiplier)
        └── OccupancyPricingStrategy  (occupancy-level adjustment)
              └── UrgencyPricingStrategy   (last-minute booking premium)
                    └── HolidayPricingStrategy  (holiday/peak season pricing)
```

Each strategy wraps the previous one, applying its own multiplier to the computed price. New strategies can be added without modifying existing code (**Open/Closed Principle**).

```java
// How it works internally
PricingStrategy strategy = new BasePricingStrategy();
strategy = new SurgePricingStrategy(strategy);
strategy = new OccupancyPricingStrategy(strategy);
strategy = new UrgencyPricingStrategy(strategy);
strategy = new HolidayPricingStrategy(strategy);

BigDecimal finalPrice = strategy.calculatePrice(inventory);
```

---

## 🔒 Security

```
Request
  ↓
CorsFilter (configurable origins)
  ↓
JWTAuthFilter (extracts & validates Bearer token)
  ↓
SecurityFilterChain
  ├── /auth/**, /hotels/**, /swagger-ui/**, /actuator/** → permitAll
  ├── /admin/**  → ROLE_HOTEL_MANAGER
  ├── /bookings/** → authenticated
  └── /users/**  → authenticated
  ↓
AccessDeniedHandler (delegates to GlobalExceptionHandler)
```

- **Password hashing:** BCrypt
- **Token format:** JWT (HMAC-SHA256)
- **Session policy:** Stateless (no cookies, no server-side sessions)
- **CORS:** Configurable via `CorsConfig.java` with allowed origins from `frontend.url` property

---

## 🗃 Database Schema

<p align="center">
  <img src="https://github.com/user-attachments/assets/bc209296-e0f2-48f9-a7ae-65d084e4cb6c" alt="Database Schema" width="720"/>
</p>

### Core Entities

| Entity | Description |
|:--|:--|
| `UserEntity` | User accounts with roles and authentication details |
| `HotelEntity` | Hotel properties with contact info and owner reference |
| `RoomEntity` | Room types within a hotel (capacity, count, base price) |
| `InventoryEntity` | Daily room availability & pricing per room type |
| `BookingEntity` | Booking records with status lifecycle tracking |
| `GuestEntity` | Guest profiles linked to users |
| `HotelMinPriceEntity` | Denormalized minimum price for search optimization |
| `HotelContactInfo` | Embeddable contact information |

### Booking Status Lifecycle

```
RESERVED  →  GUESTS_ADDED  →  PAYMENTS_PENDING  →  CONFIRMED
    ↓                                                    ↓
 EXPIRED                                            CANCELLED
(auto, 10min)                                    (manual + refund)
```

---

## 🐳 Deployment

### Docker

```bash
# Build the image
docker build -t airnest:latest .

# Run with environment variables
docker run -d \
  --name airnest \
  -p 8080:8080 \
  -e DB_URL=jdbc:postgresql://host.docker.internal:5432/airnest \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=your_password \
  -e JWT_SECRET=your-256-bit-secret \
  -e FRONTEND_URL=https://your-frontend.com \
  -e STRIPE_SECRET_KEY=sk_live_... \
  -e STRIPE_WEBHOOK_SECRET=whsec_... \
  airnest:latest
```

The Docker image uses a **multi-stage build** (Temurin 25 JDK → JRE) for a minimal production footprint.

### Production Checklist

- [x] `spring.jpa.hibernate.ddl-auto=validate` (no auto schema changes)
- [x] SQL logging disabled
- [x] Actuator exposes only `health` and `info` endpoints
- [x] Structured logging with timestamp, thread, and level
- [x] Spring profiles for environment separation (`dev`, `prod`)

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork** the repository
2. **Create** your feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

---

<p align="center">
  <sub>Built with ❤️ using Spring Boot · Designed for learning & production readiness</sub>
</p>
