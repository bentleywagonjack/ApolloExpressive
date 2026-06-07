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
  <img src="https://github.com/bentleywagonjack/ApolloExpressive/issues/1#issue-4608731813" width="350" alt="Library View Screen">
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
