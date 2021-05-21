# Questions

We didn't define any UX flow to let users edit a descrption on an existing TODO item.
Which UX flow will you define?
In your response notice the following:
1. how easy is it for users to figure out this flow in their first usage? (for example, a glowing button is more discoverable then a swipe-left gesture)
2. how hard to implement will your solution be?
3. how consistent is this flow with regular "edit" flows in the Android world?

# Answers:
I would implement the following solution: 
   1. The user swipe to the left on the item he wants to edit.
   2. The UI reveals two buttons (edit and delete) one next to the other, when the description text being pushed to the side.
   3. The user clicks on the edit button.
   4. Item gets back to normal display.
   5. The TextView of the description becomes an EditText view with the keyboard in it. 
   6. UI displays OK button at the far right corner.
   
This implementation for edit should be easy for the user to figure out in the first usage, since may apps with lists, 
implements extra actions using this method.
This implementation shouldn't be hard, but requires some knolage we still don't have (Listenning to swipe events and manipulating the UI accordingly) .



I pledge the highest level of ethical principles in support of academic excellence.  
I ensure that all of my work reflects my own abilities and not those of someone else.