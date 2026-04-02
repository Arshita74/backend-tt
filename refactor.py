import os

base_dir = r"c:\Users\HP\Documents\workspace-spring-tools-for-eclipse-4.32.2.RELEASE\class_abstract\src\main\java\com\example\demo"

mapping = {
    'controller': ['AdminController.java', 'EventController.java', 'IssueController.java', 'RoomController.java', 'ScheduleController.java', 'UserController.java'],
    'model': ['Event.java', 'Issue.java', 'Room.java', 'Schedule.java', 'User.java'],
    'repository': ['EventRepository.java', 'IssueRepository.java', 'RoomRepository.java', 'ScheduleRepository.java', 'UserRepository.java'],
    'service': ['RoomService.java', 'ScheduleService.java', 'UserService.java'],
    'config': ['CorsConfig.java']
}

imports = """
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.controller.*;
import com.example.demo.config.*;
"""

for subpkg, files in mapping.items():
    subpkg_dir = os.path.join(base_dir, subpkg)
    os.makedirs(subpkg_dir, exist_ok=True)
    
    for file in files:
        file_path = os.path.join(base_dir, file)
        if not os.path.exists(file_path):
            print(f"File not found: {file_path}")
            continue
            
        with open(file_path, "r", encoding="utf-8") as f:
            content = f.read()
            
        # Replace package declaration
        if "package com.example.demo;" in content:
            content = content.replace("package com.example.demo;", f"package com.example.demo.{subpkg};\n{imports}")
        
        new_file_path = os.path.join(subpkg_dir, file)
        with open(new_file_path, "w", encoding="utf-8") as f:
            f.write(content)
            
        os.remove(file_path)
        print(f"Moved {file} to {subpkg}")

# Also update the main application class to include imports and tests
main_class = os.path.join(base_dir, "ClassAbstractApplication.java")
if os.path.exists(main_class):
    with open(main_class, "r", encoding="utf-8") as f:
        content = f.read()
    if "import com.example.demo.model.*" not in content:
        content = content.replace("package com.example.demo;", f"package com.example.demo;\n{imports}")
        with open(main_class, "w", encoding="utf-8") as f:
            f.write(content)

test_class = r"c:\Users\HP\Documents\workspace-spring-tools-for-eclipse-4.32.2.RELEASE\class_abstract\src\test\java\com\example\demo\ClassAbstractApplicationTests.java"
if os.path.exists(test_class):
    with open(test_class, "r", encoding="utf-8") as f:
        content = f.read()
    if "import com.example.demo.model.*" not in content:
        content = content.replace("package com.example.demo;", f"package com.example.demo;\n{imports}")
        with open(test_class, "w", encoding="utf-8") as f:
            f.write(content)
