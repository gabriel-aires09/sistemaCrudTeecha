package com.example;

import com.example.binarytree.BinaryTree;
import com.example.person.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private BinaryTree tree = new BinaryTree();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        primaryStage.setTitle("Sistema Simples CRUD - Estudante and TeChá");

        // Layoutsudo
        VBox layout = new VBox();
        layout.setSpacing(10);

        // Input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Nome do meliante");
        
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Estudante", "Professor");
        typeBox.setPromptText("Tipo");

        Button addButton = new Button("Adicionar");
        Button searchButton = new Button("Procurar");
        Button removeButton = new Button("Remover");
        Button showAllButton = new Button("Mostrar usuarios");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        // Event handlers
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String type = typeBox.getValue();
           if (name.isEmpty() || type == null) {
                showAlert("Erro", "Digite o nome e escolha o tipo");
                return;
            } 
            tree.add(new Person(name, type));
            outputArea.appendText("Adicionado: " + name + " (" + type + ")\n");
            nameField.clear();
            typeBox.getSelectionModel().clearSelection();
        });

        searchButton.setOnAction(e -> {
            String name = nameField.getText();
            if (name.isEmpty()) {
                showAlert("Erro", "Por favor, forneça um nome para adicionar");
                return;
            }
            boolean found = tree.search(tree.root, name);
            outputArea.appendText("Procurando por: " + name + ": " + (found ? "Encontrado" : "Não encontrado") + "\n");
        });

        removeButton.setOnAction(e -> {
            String name = nameField.getText();
            if (name.isEmpty()) {
                showAlert("Erro", "Por favor, forneça um nome para remover");
                return;
            }
            tree.remove(name);
            outputArea.appendText("Removido: " + name + "\n");
        });

        showAllButton.setOnAction(e -> {
            outputArea.appendText("Todos os usuários:\n");
            tree.printTreeToTextArea(outputArea);
        });

        // Add to layout
        layout.getChildren().addAll(nameField, typeBox, addButton, searchButton, removeButton, showAllButton, outputArea);

        // Scene
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
