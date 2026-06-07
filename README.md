# ApolloExpressive 🎵

A minimalist, high-fidelity local music player built with a modern **Expressive Dark / Vibrant Hybrid** user interface. Designed for seamless navigation, beautiful typography, and focus on the listening experience.

---

## ✨ Features

- **Modern Aesthetic UI:** Deep purple tones matched with high-contrast lavender highlights provide a distraction-free, premium dark mode layout.
- **Unified Library & mini-Player:** Smooth visual anchoring highlights the currently active track within your music library, paired with a persistent global playback strip.
- **Immersive Now Playing Dashboard:** Large, rounded album art placeholders, precise scrubbing controls, and highly legible tactile button states.
- **Clean Audio Architecture:** Minimalist metadata engine reads and updates locally stored files directly, with elegant fallbacks for `<unknown>` tags.

---

## 📸 User Interface Preview

### Library Management View
Browse all tracks on your local drive with clear track lengths and custom active highlights.
<p align="center">
  <img src="https://private-user-images.githubusercontent.com/290085462/604116588-3a709818-cf73-4812-8e3d-59178b2c0d4c.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3ODA4NjY5NTUsIm5iZiI6MTc4MDg2NjY1NSwicGF0aCI6Ii8yOTAwODU0NjIvNjA0MTE2NTg4LTNhNzA5ODE4LWNmNzMtNDgxMi04ZTNkLTU5MTc4YjJjMGQ0Yy5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjYwNjA3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI2MDYwN1QyMTEwNTVaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zMjNlODczYTdiNjk3ZjJmZjQwOGIxMzNlM2FmMjcxOWY0YTg0NDU0YjUyZDA1OTYyOTRkNDFmZDBjMWM4OTA1JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZyZXNwb25zZS1jb250ZW50LXR5cGU9aW1hZ2UlMkZwbmcifQ.tLTCpgPqjRLhezl3cDcTUgxyszC9lM0zihyT2WRQYNY" width="350" alt="Library View Screen">
</p>

### Fully Immersive Playback Control
Tactile scrubbing layout, favorites tracking (`Heart icon`), shuffle, and repeat modes.
<p align="center">
  <img src="Screenshot_20260607-165816_ApolloExpressive.png" width="350" alt="Now Playing Screen">
</p>

---

## 🛠️ Tech Stack & Setup

- **Language:** Kotlin / Swift (Native Mobile) or React Native / Flutter (Cross-platform)
- **UI Architecture:** Declarative layout components utilizing custom deep-palette token themes.
- **Audio Core:** Low-latency localized audio player engine with dynamic background notification binding.
