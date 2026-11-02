package Assessment_B;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("EM111", "Sipho", "Tester", Positions.Junior, "IT", 25000);
    }

    @Test
    @DisplayName("raiseSalary adds 10% to the basic salary and returns the new salary")
    void raiseSalaryShouldIncreaseBasicSalary() throws HighSalaryIncrease {
        double newSalary = employee.raiseSalary(10);

        assertEquals(27500, newSalary);
        assertEquals(27500, employee.getBasicSalary());
    }

    @Test
    @DisplayName("raiseSalary throws HighSalaryIncrease when the increase is more than R5000")
    void raiseSalaryShouldThrowWhenIncreaseTooHigh() {
        assertThrows(HighSalaryIncrease.class, () -> employee.raiseSalary(25));
    }

    @Test
    @DisplayName("calcHouseAllowance returns 8% of the basic salary")
    void calcHouseAllowanceShouldBe8PercentOfBasic() {
        assertEquals(2000, employee.calcHouseAllowance());
    }

    @Test
    @DisplayName("calcGrossSalary returns basic salary plus house allowance")
    void calcGrossSalaryShouldBeBasicPLusAllowance() {
        assertEquals(27000, employee.calcGrossSalary());
    }

    @Test
    @DisplayName("calcTax returns 15% of the gross salary")
    void calcTaxShouldBe15PercentOfGross() {
        assertEquals(4050, employee.calcTax());
    }

    @Test
    @DisplayName("calcNetSalary returns gross salary minus tax")
    void calcNetSalaryShouldBeGrossMinusTax() {
        assertEquals(22950, employee.calcNetSalary());
    }

    @Test
    @DisplayName("Employee ID does not start with EM is rejected")
    void invalidEmployeeIDShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Employee("XY999", "Kabelo", "Instructor", Positions.Senior, "IT", 35000));
    }

    @Test
    @DisplayName("Basic Salary below R4000 is rejected")
    void lowBasicSalaryShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Employee("EM222", "Cya", "Intern", Positions.Junior, "IT", 3999));
    }
}