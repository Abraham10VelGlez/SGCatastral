/*    */ package edomex.gob.mx.sgcatastral.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.hibernate.cfg.AnnotationConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HibernateUtil
/*    */ {
/*    */   private static final SessionFactory sessionFactory;
/*    */   
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 26 */       sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
/*    */     }
/*    */     catch (Throwable ex) {
/* 29 */       System.err.println("Initial SessionFactory creation failed." + ex);
/* 30 */       throw new ExceptionInInitializerError(ex);
/*    */     }
/*    */   }
/*    */   
/*    */   public static SessionFactory getSessionFactory() {
/* 35 */     return sessionFactory;
/*    */   }
/*    */ }