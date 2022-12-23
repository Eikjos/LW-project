echo "Téléchargement et décompression de Java 19"
wget https://download.java.net/java/GA/jdk19.0.1/afdd2e245b014143b62ccb916125e3ce/10/GPL/openjdk-19.0.1_linux-x64_bin.tar.gz
tar xf openjdk-19.0.1_linux-x64_bin.tar.gz

echo "Lancement du backend"
./jdk-19.0.1/bin/java -jar kanban-0.0.1-SNAPSHOT.jar &

echo "L'application est accessible à l'adresse http://192.168.76.76:5001"