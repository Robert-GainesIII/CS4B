/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customExceptions;

/**
 *
 * @author bobby
 */
public class UserAlreadyExists extends Exception{
        public UserAlreadyExists(String s){
            super(s);
        }
}