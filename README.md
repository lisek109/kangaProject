## kangaProject | Crypto Spread Monitoring Automation
  
## 📌 Project Overview
kangaProject is a specialized Java-based automation tool designed to monitor and analyze market liquidity by calculating spreads for various cryptocurrency pairs within an exchange office environment.
The application serves as a real-time monitoring solution that categorizes market conditions to assist in financial decision-making or algorithmic trading adjustments. It is built with a focus on automation, precision, and performance.
## 🚀 Key Features
• Automated Real-Time Monitoring: The system executes a full analysis cycle automatically every 60 seconds to ensure data freshness.
• Intelligent Categorization: Spreads are dynamically calculated and assigned to one of three business logic categories:
    ◦ Low Spread: Spread value ≤ 2%.
    ◦ High Spread: Spread value > 2%.
    ◦ No Spread: Situations where no spread is detected.
• Reliable Logic: Robust calculation engine for financial data processing.
## 🏗 Workflow Architecture
The following diagram illustrates the automated execution cycle of the application:
graph TD
    A[Start Application] --> B{Timer: Every 1 Minute}
    B --> C[Fetch Crypto Pair Data]
    C --> D[Calculate Spread Value]
    D --> E{Categorize Spread}
    E -->|<= 2%| F[Category: Spread up to 2%]
    E -->|> 2%| G[Category: Spread above 2%]
    E -->|Zero/None| H[Category: No Spread]
    F --> I[Log/Output Result]
    G --> I
    H --> I
    I --> B
## 🛠 Tech Stack
• Core: Java (100% of the codebase).
• Build Tool: Maven for dependency management and lifecycle control.
• IDE: Developed using IntelliJ IDEA environment.
## 💻 Installation & Usage
Prerequisites
• Java JDK (recommended version 11 or higher).
• Maven installed on your local machine.
Getting Started
1. Clone the repository:
2. Build the project:
3. Run the application: The entry point of the program is located in the Main class. You can run it via your IDE or using the command line:
## 📂 Project Structure
kangaProject/
├── src/               # Application source code [3]
├── .idea/             # Project-specific settings [3]
├── pom.xml            # Maven configuration and dependencies [3]
└── README.md          # Project documentation
