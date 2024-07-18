import { defineStore } from 'pinia'
import { reactive, ref } from 'vue'

export const useRoutineModalStore = defineStore('routinemodal', () => {

    // Configuration of the components
    const configurations = reactive({
        add: {
            buttonText: 'Add your routine here!',
            titleText: 'New Routine',
            inputLabelText: 'Input your customized routine:',
            radioTitle: 'Select your desired routine\'s period:',
            botButtonText: 'Add it!',
            hasPlusIcon: true,
            hasTickIcon: true
        },
        edit: {
            buttonText: 'Edit',
            titleText: 'Edit Your Routine',
            inputLabelText: 'Your customized routine:',
            radioTitle: 'Select your desired routine\'s period:',
            botButtonText: 'Save',
            hasPlusIcon: false,
            hasTickIcon: true
        }
    })

    const periods = reactive([
        { value: '0', title: 'Daily routine', describe: 'aaa' },
        { value: '1', title: 'Weekly routine', describe: 'bbb' },
        { value: '2', title: 'Monthly routine', describe: 'ccc' },
    ])

    return {configurations}
})