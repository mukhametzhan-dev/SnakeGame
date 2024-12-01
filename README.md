Sure! Here is the humanized documentation for the design patterns used in this project, divided into three categories: Creational, Structural, and Behavioral.

### Creational Patterns

1. **Singleton Pattern**
   - **Purpose**: Ensures that a class has only one instance and provides a global point of access to it.
   - **Usage in Project**: 
     - **OrderGui**: The `OrderGui` class uses the Singleton pattern to ensure that only one instance of the `OrderGui` is created. This is useful for managing the order interface consistently throughout the application.
     - **CoffeeGui**: Similarly, the `CoffeeGui` class uses the Singleton pattern to manage the coffee interface.

2. **Builder Pattern**
   - **Purpose**: Separates the construction of a complex object from its representation, allowing the same construction process to create different representations.
   - **Usage in Project**: 
     - **ButtonBuilder**: The `ButtonBuilder` class is used to construct buttons with various properties (text, image, font size, font weight, font posture) in a step-by-step manner. This makes the button creation process more flexible and readable.

### Structural Patterns

3. **Adapter Pattern**
   - **Purpose**: Allows incompatible interfaces to work together by converting the interface of a class into another interface that a client expects.
   - **Usage in Project**: 
     - **OrderListAdapter**: The `OrderListAdapter` class adapts the `OrderList` class to the `OrderListDisplay` interface. This allows the `OrderList` objects to be displayed in the `TableView` in the `OrderListGui` class without modifying the existing `OrderList` class.

4. **Decorator Pattern**
   - **Purpose**: Adds additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.
   - **Usage in Project**: 
     - **ImageButtonDecorator**: The `ImageButtonDecorator` class extends the functionality of JavaFX `Button` by adding an image to it. This allows buttons to have both text and images without modifying the original `Button` class.

### Behavioral Patterns

5. **Observer Pattern**
   - **Purpose**: Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
   - **Usage in Project**: 
     - **AdministratorObserver**: The `AdministratorObserver` interface is used to update the list of administrators in the `AdministratorGui` class. When the list of administrators changes, the observer is notified and updates the display accordingly.

6. **Strategy Pattern**
   - **Purpose**: Defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
   - **Usage in Project**: 
     - **CoffeeDisplayStrategy**: The `CoffeeDisplayStrategy` interface and its implementations (`CoffeeImageDisplayStrategy` and `CoffeeTextDisplayStrategy`) are used to display coffee details in different ways in the `CoffeeGui` class. This allows the display strategy to be changed dynamically at runtime.

These design patterns help in organizing the code, making it more modular, flexible, and easier to maintain. Each pattern addresses a specific problem and provides a structured solution that enhances the overall design of the application.
