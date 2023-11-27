// i18n.js
import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

// Import your translation files
import translationEN from '../locales/en/translation.json';
import translationFR from '../locales/fr/translation.json';

const resources = {
    en: {
        translation: translationEN,
    },
    fr: {
        translation: translationFR,
    },
};

i18n
    .use(initReactI18next) // passes i18n down to react-i18next
    .init({
        resources,
        lng: 'en', // default language
        fallbackLng: 'en', // fallback language
        interpolation: {
        escapeValue: false, // not needed for React as it escapes by default
        },
    });

export default i18n;