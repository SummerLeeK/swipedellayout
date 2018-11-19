// IMyAidlInterface.aidl
package study.lee.aidlproject;

import study.lee.aidlproject.helper.Book;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void addBook(in Book book);

    List<Book> getBooks();
}
