def call(String credId, String imageName) {
    withCredentials([usernamePassword(
        credentialsId: credId,
        usernameVariable: 'DOCKERHUB_USER',
        passwordVariable: 'DOCKERHUB_PASS'
    )]) {
        sh """
            echo "\$DOCKERHUB_PASS" | docker login -u "\$DOCKERHUB_USER" --password-stdin
            docker tag ${imageName} \$DOCKERHUB_USER/${imageName}:latest
            docker push \$DOCKERHUB_USER/${imageName}:latest
        """
    }
}
