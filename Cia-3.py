from functools import reduce   # reduce used to add marks

class Student:
    def __init__(self, name, marksbox):
        self.name = name
        self.marksbox = marksbox

        # get only marks
        self.marklist = list(self.marksbox.values())

        # total using reduce + lambda
        self.total = reduce(lambda a, b: a + b, self.marklist)

        # average
        self.avg = self.total / len(self.marklist)

        # grade
        self.grade = self.getgrade(self.avg)

    # grade finder
    def getgrade(self, avg):
        if avg >= 90:
            return "A+"
        elif avg >= 80:
            return "A"
        elif avg >= 70:
            return "B"
        elif avg >= 60:
            return "C"
        else:
            return "F"

    # show student info
    def show(self):
        print("\nName:", self.name)
        print("Marks:", self.marksbox)
        print("Total:", self.total)
        print("Avg:", round(self.avg, 2))
        print("Grade:", self.grade)


class GradeTool:
    def __init__(self):
        self.kids = []      # list of all students

    # add student
    def add(self):
        name = input("Enter name: ")

        subs = ("Math", "Science", "English")
        box = {}

        for s in subs:
            m = int(input(f"Marks in {s}: "))
            box[s] = m

        st = Student(name, box)
        self.kids.append(st)

        print("\nStudent added!")

    # recursive print
    def recprint(self, n=0):
        if n == len(self.kids):
            return
        self.kids[n].show()
        self.recprint(n + 1)

    # class stats
    def stats(self):
        if not self.kids:
            print("\nNo data found!")
            return

        print("\n--- CLASS STATS ---")

        avgs = [k.avg for k in self.kids]

        print("High Avg:", max(avgs))
        print("Low Avg:", min(avgs))

        passlist = list(filter(lambda k: k.avg >= 40, self.kids))

        print("Pass:", len(passlist))
        print("Fail:", len(self.kids) - len(passlist))

    # comprehension demo
    def demo(self):
        print("\n--- COMPREHENSION DEMO ---")

        namemap = {k.name: k.grade for k in self.kids}
        print("Name → Grade:", namemap)

        gradeset = {k.grade for k in self.kids}
        print("Grade Set:", gradeset)

    # menu
    def menu(self):
        while True:
            print("\n===== STUDENT GRADE ANALYZER =====")
            print("1. Add Student")
            print("2. Show All Students")
            print("3. Class Stats")
            print("4. Comprehension Demo")
            print("5. Exit")

            ch = input("Enter choice: ").strip()

            if ch == "1":
                self.add()
            elif ch == "2":
                if not self.kids:
                    print("\nNo students yet!")
                else:
                    self.recprint()
            elif ch == "3":
                self.stats()
            elif ch == "4":
                self.demo()
            elif ch == "5":
                print("Bye!")
                break
            else:
                print("Wrong choice!")


if __name__ == "__main__":
    tool = GradeTool()
    tool.menu()


