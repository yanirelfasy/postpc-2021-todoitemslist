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

  @Test
  public void when_changingItemToDoneAndBackToProgress_then_itemStatusIsInProgress(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    TodoItem addedItem = holderUnderTest.getCurrentItems().get(0);
    holderUnderTest.markItemDone(addedItem);
    holderUnderTest.markItemInProgress(addedItem);

    // verify
    Assert.assertFalse(addedItem.isDone());
  }

  @Test
  public void when_removingItem_then_itemIsRemoved(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    TodoItem addedItem = holderUnderTest.getCurrentItems().get(0);
    holderUnderTest.deleteItem(addedItem);

    // verify
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_addingItem_then_itemIsFirst(){
    try{
      // setup
      TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();

      // test
      holderUnderTest.addNewInProgressItem("do shopping");
      Thread.sleep(200);
      holderUnderTest.addNewInProgressItem("do shopping2");
      Thread.sleep(200);
      holderUnderTest.addNewInProgressItem("do shopping3");

      TodoItem addedItem = holderUnderTest.getCurrentItems().get(0);
      // verify
      Assert.assertEquals(2, addedItem.getItemID());
    }
    catch (Exception e){
      System.out.println("TEST EXCEPTION");
    }
  }

  @Test
  public void when_addingTwoItemsAndTheLatestIsDone_then_theFirstItemIsTheOldest(){
    try{
      // setup
      TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();

      // test
      holderUnderTest.addNewInProgressItem("do shopping");
      Thread.sleep(200);
      holderUnderTest.addNewInProgressItem("do shopping2");
      TodoItem addedItem = holderUnderTest.getCurrentItems().get(0);
      holderUnderTest.markItemDone(addedItem);
      TodoItem firstItem = holderUnderTest.getCurrentItems().get(0);
      // verify
      Assert.assertEquals(0, firstItem.getItemID());
    }
    catch (Exception e){
      System.out.println("TEST EXCEPTION");
    }
  }

  @Test
  public void when_removingItemThatIsNotInTheList_then_listStaysTheSame(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    TodoItem addedItem = new TodoItem("dummy", 1);
    holderUnderTest.deleteItem(addedItem);

    // verify
    Assert.assertEquals(1, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_changingItemThatIsNotInTheList_then_listStaysTheSame(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    TodoItem addedItem = new TodoItem("dummy", 1);
    holderUnderTest.markItemDone(addedItem);

    // verify
    Assert.assertEquals(1, holderUnderTest.getCurrentItems().size());
    Assert.assertFalse(holderUnderTest.getCurrentItems().get(0).isDone());
  }

}