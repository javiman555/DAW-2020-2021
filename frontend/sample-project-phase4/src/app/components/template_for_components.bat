@echo off
MKDIR %1
CD %1
COPY nul %1.component.css> nul
COPY nul %1.component.html> nul
COPY nul %1.component.ts> nul