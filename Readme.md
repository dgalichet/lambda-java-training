# Initiation à Lambda

## 1. Créer un bucket
Dans ce bucket, créer 3 répertoires :
 - lambdas
 - raw
 - thumbnails
 
Assurez-vous que l'instance que vous utilisez possède bien un IAM Role et que ce rôle donne l'accès (notamment `putObject`) au bucket.

## 2. Initialisation du code de la Lambda
Compléter le code de la lambda `HelloS3` pour qu'elle produise une miniature.

Regarder le code de `ImageProcessor` et comprendre son fonctionnement.

Une fois la Lambda `HelloS3` terminée, compilez et packagez le projet :
```mvn clean compile package``` ce qui produit un _Fat Jar_ dans le répertoire `target`.

## 3. Upload du package de la Lambda
A l'aide de la CLI AWS, copiez le fichier `lambda-java-sample-<version>.jar` dans le répertoire lambdas du bucket créé précédement.

## 4. Créer un rôle pour l'exécution de la Lambda
 - Dans IAM, dans le menu *Role* faites *Create New Role*
 - *Role Name*: définir votre nom (ex:  `helloS3-lambda-role`)
 - Dans *AWS Service Roles* choisir *AWS Lambdas*
 - Rechercher `AWSLambdaBasicExecutionRole` puis le sélectionner et enfin validez la création du Role.

Ensuite, recherchez votre rôle et ajoutez une inline policy pour :
```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "s3:GetObject"
            ],
            "Resource": [
                "arn:aws:s3:::<bucket>/raw/*"
            ]
        },
        {
            "Effect": "Allow",
            "Action": [
                "s3:PutObject"
            ],
            "Resource": [
                "arn:aws:s3:::<bucket>/thumbnails/*"
            ]
        }
    ]
}
```

## 5. Création de la lambda
Dans la console AWS -> Lambdas, créez une nouvelle fonction Lambda.

 - *Select blueprint*: choisir le `Blank Function`
 - *Configure trigger*: Choisir S3
   - *Bucket*: choisir votre bucket (créé en Step 1)
   - *Event type*: `Object Created (All)`
   - *Prefix*: `raw/`
   - *Suffix*: `.jpg`
 - *Configure function*:
   - *Name*: arbitraire
   - *Description*: arbitraire
   - *Runtime*: Java8
   - *Code entry type*: Upload a file from S3
   - *S3 link URL*: le lien vers l'archive Jar Lambda que vous avez précédemment uploaded
   - *Handler*: `com.scaleo.lambda.HelloS3::s3EventHandler`
   - *Role*: sélectionner `choose an existing role`
   - *Existing Role*: sélectionner le rôle que vous avez créé au Step 4
   - *Memory*: 512MB
   - *Timeout*: 15s
   - No VPC
   - *KMS Key*: (default aws/lambda)
 
## 6. Création d'une vignette
Postez une image dans le répertoire `raw/` et vérifiez que l'image est correctement traitée et que le résultat arrive dans le répertoire `thumbnails`

## 7. Exercice supplémentaire - variables d'environnement
Il est possible dans Lambda d'utiliser des variables d'environnement.

Introduire deux variables `IMG_SRC` et `IMG_TARGET` pour spécifier le répertoire d'entrée et de sortie de la Lambda.

HINT: regardez du côté de `System.getenv`
