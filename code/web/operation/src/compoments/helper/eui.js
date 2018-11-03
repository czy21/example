import {MessageBox} from 'element-ui'

const inform = function (text, callback) {
  MessageBox.alert(text, '提示', {
    type: 'info',
    dangerouslyUseHTMLString: true,
    showClose: false,
    callback: callback
  })
}

const eui = {
  inform
}
export default eui
