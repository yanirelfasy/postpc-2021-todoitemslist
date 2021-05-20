package exercise.android.reemh.todo_items;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class TodoItemsHolderImplTest {
  @Test
  public void when_addingTodoItem_then_callingListShouldHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    Assert.assertEquals(1, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_changingItemToDone_then_itemStatusIsUpdated(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    TodoItem addedItem = holderUnderTest.getCurrentItems().get(0);
    holderUnderTest.markItemDone(addedItem);

    // verify
    Assert.assertTrue(addedItem.isDone());
  }

  // TODO: add at least 9 more tests to verify correct behavior of your implementation of `TodoItemsHolderImpl` class
}