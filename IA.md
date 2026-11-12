# Déclaration d’utilisation de l’IA – TP2 LangChain4j

**Étudiant** — Omar Zouitina  
**Périmètre** — Tests 1 à 3 et application Web  
**Outil d’assistance** — ChatGPT  
**Part estimée de l’assistance de l’IA** — 35 % du travail. Ce chiffre est une estimation globale.

## Utilisation de ChatGPT

J’ai utilisé ChatGPT pour obtenir des exemples de code Java pour les premiers appels à Gemini, le calcul du coût à partir des tokens et la traduction avec `PromptTemplate`. Pour la partie Web, l’outil a proposé du code pour adapter le backing bean `Bb`, écrire le client `LlmClient` et gérer la mémoire de conversation avec LangChain4j. Il m’a aussi aidé à diagnostiquer des erreurs de compilation Maven et à repérer l’emplacement des fichiers JavaScript dans le projet Web.

## Intégration et vérifications

J’ai intégré les modifications dans IntelliJ, exécuté les trois tests et vérifié leurs sorties dans la console. J’ai compilé le projet Web, déployé le fichier WAR sur Payara et vérifié dans le navigateur que Gemini répondait, que le contexte était conservé entre deux questions et que le rôle de traducteur fonctionnait après la création d’un nouveau chat. J’ai publié les deux projets sur GitHub.

Gemini est le modèle appelé par les programmes du TP. Le taux indiqué concerne l’assistance de ChatGPT pendant la réalisation du travail.
