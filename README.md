A console-based Aadhaar Management System built entirely in Core Java, demonstrating real-world concepts like Encapsulation, Collections, File Handling, Serialization, Exception Handling, Streams, Multithreading, and Logging.

This project simulates a simplified version of India’s Aadhaar data portal — fully interactive via the console — built as part of my Java learning journey. 🚀


---

📚 Features

🧩 Core Java Concepts

Encapsulation → Private fields with getters/setters and OTP verification before access

Collections → ArrayList for dynamic user management (Add / Remove / Update)

Serialization → Persistent data storage in users.ser

Custom Exceptions → InvalidDataException for Aadhaar & contact validation

Validation Rules → Aadhaar = 12 digits, Contact = 10 digits


⚙️ Advanced Features

Java 8 Streams → Search users by name or Aadhaar

Multithreading → Simulated OTP sending in background using Runnable

Admin Login → Basic username-password security

Action Logging → Logs all operations (portal.log) with timestamps

Colored Console Output → ANSI color codes for success, warnings, and errors

Masked Aadhaar Display → Shows only last 4 digits for privacy



---

🧠 Learning Outcomes

Through this project I practiced:

Core Java OOP principles (Encapsulation, Abstraction)

Collections Framework (ArrayList)

File Handling & Object Serialization

Exception Handling & Input Validation

Streams & Functional Programming (Java 8)

Multithreading using Thread & Runnable

Logging and simple console UI formatting



---

📂 Project Structure

├── Adhaar4.java              # Model class with encapsulation, validation & OTP
├── Portal4.java              # Main application (menus, CRUD, search, persistence)
├── OtpSender.java            # Runnable class for OTP simulation (multithreading)
├── InvalidDataException.java # Custom exception for validation
├── Colors.java               # ANSI color helper class
├── users.ser                 # Serialized user data (auto-generated)
├── portal.log                # Log file of actions (auto-generated)
└── README.md                 # Project documentation


---

🖥️ How to Run the Project

Step 1️⃣ – Clone or Download

git clone https://github.com/<nikhilsinghp>/Java_Mini_Projects.git
cd Java_Mini_Projects

Step 2️⃣ – Compile

javac *.java

Step 3️⃣ – Run

java Portal4


---

🔐 Admin Login (Demo Credentials)

Field Value

Username admin
Password password123



---

🧾 Sample Run (Console Preview)

===== Aadhaar Portal (v4.0) =====
Enter admin username: admin
Enter admin password: password123
✅ Login successful!

Main Menu
1. Get info
2. Set info
3. Add user
4. Remove user
5. Search user
6. Exit
Enter your choice: 3
Enter name: Aditi
Enter Aadhaar number (12 digits): 567856785678
Enter address: Pune
Enter contact number (10 digits): 9123456789
✅ User added: Aditi
💾 Users saved.

Enter registered contact number: 9123456789
📨 Sending OTP...
📥 OTP delivered (for demo): 4321
Enter OTP: 4321
✅ Verification successful.
Aadhaar: 567856785678


---

🗂️ Sample portal.log

Wed Oct 06 13:05:45 UTC 2025 - Admin logged in
Wed Oct 06 13:05:47 UTC 2025 - Added user: Aditi
Wed Oct 06 13:06:02 UTC 2025 - Viewed Aadhaar for Aditi
Wed Oct 06 13:06:15 UTC 2025 - Updated contact for Aman
Wed Oct 06 13:06:45 UTC 2025 - Searched users for: Nikhil
Wed Oct 06 13:07:12 UTC 2025 - Saved users to file (count=4)


---

🪄 Tech Stack

Component Technology

Language Java 17+
Type Console Application
Paradigm Object-Oriented Programming
Data Storage Serialization (users.ser)
Logging FileWriter (portal.log)

GitHub (https://github.com/)
GitHub · Build and ship software on a single, collaborative platform
Join the world's most widely adopted, AI-powered developer platform where millions of developers, businesses, and the largest open source community build software that advances humanity.
