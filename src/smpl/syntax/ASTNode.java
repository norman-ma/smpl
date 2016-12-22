package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

/**
 *
 * @author newts
 */
public abstract class ASTNode {
    
    /**
     * Visit this expression (subtree rooted at this node in the AST) using a
     * visitor and some state that it might need.
     * @param <S> The type of the state to be passed.
     * @param <T> The type of the return value of the visitor instance
     * @param v The visitor instance
     * @param state The state of the computation inherited from the parent of 
     * this node.
     * @return The result of the visitor calling its appropriate method 
     * corresponding to the specific subclass of expression that this node 
     * represents.
     * @throws SMPLException If a runtime error occurs.
     */
    public abstract <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException ;

    @Override
    public abstract String toString();

}
