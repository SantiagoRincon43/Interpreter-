package com.mycompany.patroninterpretor.Demo;

import com.mycompany.patroninterpretor.Expressions.TerminalExpression;
import com.mycompany.patroninterpretor.Expressions.OrExpression;
import com.mycompany.patroninterpretor.Expressions.Expression;
import com.mycompany.patroninterpretor.Expressions.AndExpression;

public class InterpreterDemo {
    
    // Regla de ejemplo: El usuario debe ser ADMIN o MANAGER
    public static Expression getAdminOrManagerExpression() {
        Expression admin = new TerminalExpression("ADMIN");
        Expression manager = new TerminalExpression("MANAGER");
        return new OrExpression(admin, manager);
    }

    // Regla de ejemplo: El usuario debe ser ADMIN y también USER (Rol dual)
    public static Expression getAdminAndUserExpression() {
        Expression admin = new TerminalExpression("ADMIN");
        Expression user = new TerminalExpression("USER");
        return new AndExpression(admin, user);
    }

    public static void main(String[] args) {
        Expression isPrivileged = getAdminOrManagerExpression();
        Expression isStrict = getAdminAndUserExpression();

        System.out.println("--- PRUEBAS DE VALIDACIÓN DE PERMISOS ---");
        
        String context1 = "ADMIN";
        System.out.println("¿El contexto '" + context1 + "' tiene acceso privilegiado? " 
            + isPrivileged.interpret(context1)); // Debería ser true

        String context2 = "USER";
        System.out.println("¿El contexto '" + context2 + "' tiene acceso privilegiado? " 
            + isPrivileged.interpret(context2)); // Debería ser false

        String context3 = "ADMIN, USER";
        System.out.println("¿El contexto '" + context3 + "' cumple el rol estricto (Admin + User)? " 
            + isStrict.interpret(context3)); // Debería ser true
    }
}
