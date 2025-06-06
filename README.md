# MyContactPro

Une application Android complète de gestion de contacts professionnels développée en Java.

## 📱 Fonctionnalités

### Gestion des contacts
- **Ajouter des contacts** : Enregistrez nom, prénom, société, adresse, téléphone, email et secteur d'activité
- **Consulter les détails** : Visualisez toutes les informations d'un contact
- **Modifier les contacts** : Mettez à jour les informations existantes
- **Marquer comme favoris** : Identifiez vos contacts prioritaires

### Actions rapides
- **Appels téléphoniques** : Composition directe depuis l'application
- **Envoi de SMS** : Messagerie intégrée
- **Géolocalisation** : Accès aux permissions de localisation

### Interface utilisateur
- Liste scrollable avec RecyclerView
- Navigation fluide entre les écrans
- Barre d'outils avec menu d'actions

## 🛠️ Technologies utilisées

- **Langage** : Java
- **Framework** : Android SDK
- **Base de données** : Room (SQLite)
- **Interface** : Material Design Components
- **Architecture** : MVVM avec DAO Pattern
- **Minimum SDK** : Android 13 (API 33)
- **Target SDK** : Android 15 (API 35)

## 📋 Structure de l'application

### Activités principales
- `MainActivity` : Écran d'accueil avec liste des contacts
- `AddContactActivity` : Ajout de nouveaux contacts
- `DetailActivity` : Affichage des détails d'un contact
- `UpdateContactActivity` : Modification des contacts existants

### Modèle de données
- `Contact` : Entité principale avec tous les champs
- `ContactDAO` : Interface d'accès aux données
- `ContactDatabase` : Configuration de la base Room
- `ContactAdapter` : Adaptateur pour la RecyclerView

## 🚀 Installation

1. Clonez le repository
2. Ouvrez le projet dans Android Studio
3. Synchronisez les dépendances Gradle
4. Compilez et installez sur un appareil Android 13+

## 📋 Permissions requises

- `CALL_PHONE` : Pour passer des appels téléphoniques
- `SEND_SMS` : Pour envoyer des messages
- `ACCESS_FINE_LOCATION` : Pour les fonctionnalités de géolocalisation

## 🎯 Utilisation

1. **Ajouter un contact** : Utilisez le bouton dans la barre d'outils
2. **Consulter un contact** : Tapez sur n'importe quel contact dans la liste
3. **Modifier un contact** : Depuis l'écran de détails, utilisez l'option de modification
4. **Actions rapides** : Appelez ou envoyez un SMS directement depuis l'application

---
