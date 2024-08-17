# Kbd34

Kbd34 started as a project to create a 12-keys keyboard, and it evolved
to become a keyboard creator application.

The objective is to have an application able to display a keyboard that matches
the user's specific needs.
It should provide a default implementation for a 12-keys keyboard with a T9-like
completion, but be build around an engine which reads JSON files, store the keyboards
configuration and dictionaries in a database and display the keyboards.

## Use cases

Kbd34 is a project developed for a personal usage, but it might be useful to you if:

- You are looking for a keyboard similar to the ones we had in 2000s, with keys
  as big as your fingers and a T9-like completion.
- You are never better served than by yourself. All keyboards seem to be designed
  by idiots, and only you know what the keyboard of your dreams looks like. The
  built-in keyboard can probably be improved to better fit your needs, you're looking
  for missing characters? The order of punctuation characters doesn't match your
  usage? The size, order or color of the keys aren't optimized for your usage?
  You need a key to switch between keyboards?
  If I manage to implement this application, all of this should have a solution.
- You are testing keyboards, thinking about Human-Computer Interaction, this
  application could help you to quickly create a keyboard prototype and try it.

## TODO

To achieve the objective, the following actions should be done, in this order.

- [ ] Implementation of KeyboardConfig & KeyConfig. These classes are an internal
  representation of the keyboard, the keys' layout, characters mapping
  etc.
  Note: KeyConfig shouldn't contain char[], but String[] instead. This way, a key
  can print "a", or "BTW, I use Arch", it's up to the keyboard.
  State: Work In Progress.
- [ ] Usage of KeyboardConfig & KeyConfig in the IME to achieve the following behaviours:
  - Conversion from a KeyboardConfig to a KeyboardView, to be able to display dynamic
	keyboards without using the XML files.
	State: Not started, didn't even check if it's doable.
  - Follow the behaviours defined in the keyboard configurations. Example: key 2
    can print either a, b or c, based on the number of pressures on the key. Key 3
	can print either a smile emoji, a angry emoji, or the letter D, by keeping the
	key pressed during 0.5 second. It should be done using the KeyboardConfig & KeyConfig
	fields values.
	Notes: Keyboards could have any number of keys, even or odd, and each row
           can have a different number of keys. Each key could have an identifier,
		   defined in the configuration files with a default value of `[row].[column]`.
	State: Didn't check if it's doable yet.
- [ ] Integrate [Keyboard][keyboard], [Key][key] & [KeyboardView][keyboard-view]
  in the project. These classes are part of the Android API, but are now
  deprecated. The classes can't be dragged & dropped, some adjustments have
  to be done, at least in the imports. Then, the project should depend on these
  classes instead of the ones provided by the API.
  Note: The classes are part of AOSP, which is licensed under Apache 2.0. The
  project is licensed under Zero Clause BSD.
  State: Not started.
- [ ] Think about replacing the classes above. The license could be a problem.
  At least, subclasses should be created, to customize behaviours and
  appearance. Example: a sublabel would be useful to display both "2" and
  "abc" on the key. The classes have to fit well to keep the engine extensible,
  add attributes, behaviours etc.
  State: Not started.
- [ ] Declare keyboards as system keyboards. It should be possible to load and
  save several keyboards, and switch between them. Example: A 12-keys keyboard
  built-in the application, and a custom 15 keys with more characters, imported
  via a JSON file.
  An option is to expose each keyboard to the system, and have a setting in the
  application's settings to enable a keyboard.

## Remarks to myself

The remarks in this section could become TODO items in the future, but for the
moment they are only remarks & ideas to remember.

1. Android seems to have a built-in dictionary, it's able to underline words in
   red when they are incorrect in a text are, even when a custom keyboard is
   enabled. Is it possible to use this built-in dictionary?
2. For completion, a dictionary will be required. Dictionaries are
   keyboard-agnostic. The apparently built-in Android dictionary could
   be used, and / or dictionaries could be imported as text files by the users.
3. One or several dictionaries could be enabled at the same time, it's up to the
   user. Default dictionaries could be provided by languages. Anyway, the
   keyboards shouldn't have any information or impact on the dictionaries. When a
   user spends time adding missing words and shortcuts in the dictionaries, they
   deserve to use them, whatever keyboard is enabled.
4. Dictionaries could be imported as well as exported, under the form of CSV
   files. The CSV contains the words + the keys combinations for a given keyboard.
   This way, it's possible to export the dictionary + the shortcuts and import
   them on another phone.
5. A UI to edit dictionaries. Words and shortcuts could be added, edited or removed.
6. A UI to edit keyboards? It would allow quick modifications of keyboards.
   If a non-technical user wants to change the order of the punctuation
   characters on the 12-keys keyboard, it would be a shame to have to copy and
   edit the JSON file of the keyboard. Editing built-in or imported keyboards
   would be achieved by cloning the keyboard, enabling it instead of the
   previous one and letting the user editing key's fields.
7. When testing a newly imported or edited keyboard, ask the user to confirm
   after X seconds if the keyboards is usable and can remain active. Otherwise,
   the previous keyboard can be reactivated. This way, the user isn't stuck with
   a broken keyboard.
8. Shortcuts in dictionaries. Dictionaries can be imported as text files, but are
   stored as a keys -> word(s) mapping. Example: 53926273 -> keyboard. By pressing
   the 5, the 3, etc, on a 12-keys keyboard, a T9-like completion would be able to
   guess that you probably want to type "keyboard". This combination can be computed
   when the dictionary is imported, so the user doesn't have to worry about defining
   it. Since the completion is key-based, could be possible to set shortcuts. Example:
   68 -> No, thanks. By pressing 6 and then 8 (letters of n and t, respectively), the
   completion could suggest "No, thanks".
9. Keys combination are keyboard-specific. In dictionaries and in shortcuts, keys
   combination have to match the keys' identifiers of the current keyboard,
   so it's necessary to compute the combination for all known words when a keyboard is
   enabled, and the shortcuts are available in completion only when the corresponding
   keyboard is enabled.


## License

This project is licensed under the BSD Zero Clause License, you can find
it contained in LICENSE.txt at the root of the project.


[keyboard-view]: https://android.googlesource.com/platform/frameworks/base.git/+/refs/heads/main/core/java/android/inputmethodservice/KeyboardView.java
[keyboard]: https://android.googlesource.com/platform/frameworks/base.git/+/refs/heads/main/core/java/android/inputmethodservice/Keyboard.java
[key]: https://android.googlesource.com/platform/frameworks/base.git/+/refs/heads/main/core/java/android/inputmethodservice/Keyboard.java#242
