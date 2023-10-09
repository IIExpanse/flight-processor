# Flight-Processor
---
Test task realization. Allows storing multiple FlightRule objects and filter supplied Flight objects according to rules' logic.<br>
Allows assigning priority rating to rules to run "cheaper" rules first (and stop process early if checks fail).<br>
### Features:
- FlightTester that allows to filter supplied flights according to stored rules;
- storage that allows adding and removing rules used for filtering purposes;
- storage implementations include unordered (for faster rule adding / removal) and ordered (for prioritized checks).

Stack: Java 17, Junit 5.
