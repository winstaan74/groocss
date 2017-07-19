/*! styles from github | MIT License */
/* Converted to groocss by Adam L. Davis, 2016 */
sg '*', {
  boxSizing 'border-box'
}

def defaultFont = '"open sans", "helvetica neue", helvetica, arial, sans-serif'
def monoFont = 'consolas, "liberation mono", menlo, courier, monospace'
def textColor = c('#606c71')
def linkColor = c('#1e6bb8')

body {
  padding 0
  margin 0
  fontFamily defaultFont
  fontSize 16.px
  lineHeight 1.5
  color textColor
}

a {
  color linkColor
  textDecoration 'none'
}
a%hover {
  textDecoration 'underline'
}

_.btn {
  display 'inline-block'
  marginBottom 1.rem
  color white.alpha(0.7)
  backgroundColor white.alpha(0.08)
  borderColor white.alpha(0.2)
  borderStyle 'solid'
  borderWidth 1.px
  borderRadius '0.3rem'
  transition 'color 0.2s, background-color 0.2s, border-color 0.2s'
}
_.btn + _.btn {
  marginLeft '1rem'
}

sg '.btn:hover', {
  color rgba(255, 255, 255, 0.8)
  textDecoration 'none'
  backgroundColor rgba(255, 255, 255, 0.2)
  borderColor rgba(255, 255, 255, 0.3)
}

media 'screen and (min-width: 64em)', {
  _.btn { padding '0.75rem 1rem' }
}

media 'screen and (min-width: 42em) and (max-width: 64em)', {
  _.btn {
    padding '0.6rem 0.9rem'
    fontSize 0.9.rem }
}

media 'screen and (max-width: 42em)', {
  _.btn {
    display 'block'
    width '100%'
    padding '0.75rem'
    fontSize '0.9rem'
  }
  _.btn + _.btn {
    marginTop '1rem'
    marginLeft 0
  }
}

sg '.page-header', {
  color '#fff'
  textAlign 'center'
  backgroundColor '#159957'
  backgroundImage 'linear-gradient(120deg, #112233, #a5b9c7)'
}

media 'screen and (min-width: 64em)', {
  sg '.page-header', {
    padding '5rem 6rem' } }

media 'screen and (min-width: 42em) and (max-width: 64em)', {
  sg '.page-header', {
    padding '3rem 4rem' } }

media 'screen and (max-width: 42em)', {
  sg '.page-header', {
    padding '2rem 1rem' } }

sg '.project-name', {
  marginTop 0
  marginBottom '0.1rem'
}

media 'screen and (min-width: 64em)', {
  sg '.project-name', { fontSize 3.25.rem }
}

media 'screen and (min-width: 42em) and (max-width: 64em)', {
  sg '.project-name', { fontSize 2.25.rem }
}

media 'screen and (max-width: 42em)', {
  sg '.project-name', { fontSize 1.75.rem }
}

sg '.project-tagline', {
  marginBottom '2rem'
  fontWeight 'normal'
  opacity 0.7
}

media 'screen and (min-width: 64em)', {
  sg '.project-tagline', { fontSize 1.25.rem }
}

media 'screen and (min-width: 42em) and (max-width: 64em)', {
  sg '.project-tagline', { fontSize 1.15.rem }
}

media 'screen and (max-width: 42em)', {
  sg '.project-tagline', { fontSize 1.rem }
}

def MC = sel('.main-content')
MC %firstChild {
  marginTop 0
}
MC ^ img {
  maxWidth '100%'
}
MC ^h1 | MC ^h2 | MC ^h3 | MC ^h4 | MC ^h5 | MC ^h6 {
  marginTop '2rem'
  marginBottom '1rem'
  fontWeight 'normal'
  color '#159957'
}
MC ^p {
  marginBottom '1em'
}
MC ^code {
  padding '2px 4px'
  fontFamily monoFont
  fontSize '0.9rem'
  color '#383e41'
  backgroundColor '#f3f6fa'
  borderRadius '0.3rem'
}
MC ^pre {
  padding '0.8rem'
  marginTop 0
  marginBottom '1rem'
  font "1rem $monoFont"
  color '#567482'
  wordWrap 'normal'
  backgroundColor '#f3f6fa'
  border 'solid 1px #dce6f0'
  borderRadius '0.3rem'
}
MC ^pre >> code {
  padding 0
  margin 0
  fontSize '0.9rem'
  color '#567482'
  wordBreak 'normal'
  whiteSpace 'pre'
  background 'transparent'
  border 0
}
MC ^ _.highlight {
  marginBottom '1rem'
}
MC ^ _.highlight ^ pre {
  marginBottom 0
  wordBreak 'normal'
}
sg (MC ^ _.highlight ^pre | MC ^pre) {
  padding '0.8rem'
  overflow 'auto'
  fontSize '0.9rem'
  lineHeight 1.45
  borderRadius '0.3rem'
}
sg("$MC pre code, $MC pre tt") {
  display 'inline'
  maxWidth 'initial'
  padding 0
  margin 0
  overflow 'initial'
  lineHeight 'inherit'
  wordWrap 'normal'
  backgroundColor 'transparent'
  border 0
}
sg "$MC pre code:before,$MC pre code:after,$MC pre tt:before,$MC pre tt:after",{
  content 'normal'
}
MC ^ ul | MC ^ ol {
  marginTop 0
}
MC ^ blockquote {
  padding '0 1rem'
  marginLeft 0
  color '#819198'
  borderLeft '0.3rem solid #dce6f0'
}
MC ^ blockquote >> firstChild {
  marginTop 0
}
MC ^ blockquote >> lastChild {
  marginBottom 0
}
MC ^ table {
  display 'block'
  width '100%'
  overflow 'auto'
  wordBreak 'normal'
  wordBreak 'keep-all'
}
MC ^table ^th {
  fontWeight 'bold'
}
MC ^table ^th | MC ^table ^td {
  padding '0.5rem 1rem'
  border '1px solid #e9ebec'
}
MC ^dl {
  padding 0
}
MC ^dl ^dt {
  padding 0
  marginTop '1rem'
  fontSize '1rem'
  fontWeight 'bold'
}
MC ^dl ^dd {
  padding 0
  marginBottom '1rem'
}
MC ^hr {
  height '2px'
  padding 0
  margin '1rem 0'
  backgroundColor '#eff0f1'
  border 0
}

media 'screen and (min-width: 64em)', {
  sg '.main-content', {
    maxWidth '64rem'
    padding '2rem 6rem'
    margin '0 auto'
    fontSize 1.1.rem }
}

media 'screen and (min-width: 42em) and (max-width: 64em)', {
  sg '.main-content', {
    padding '2rem 4rem'
    fontSize 1.1.rem
  }
}

media 'screen and (max-width: 42em)', {
  sg '.main-content', {
    padding '2rem 1rem'
    fontSize 1.rem
  }
}

def SF = '.site-footer'

sg(SF) {
  paddingTop 2.rem
  marginTop 2.rem
  borderTop 'solid 1px #eff0f1'
}

sg '.site-footer-owner', {
  display 'block'
  fontWeight 'bold'
}

sg '.site-footer-credits', {
  color '#819198'
}

media 'screen and (min-width: 64em)', {
  sg SF, {
    fontSize 1.rem
  }
}

media 'screen and (min-width: 42em) and (max-width: 64em)', {
  sg SF, {
    fontSize 1.rem
  }
}

media 'screen and (max-width: 42em)', {
  sg SF, {
    fontSize 0.9.rem
  }
}
